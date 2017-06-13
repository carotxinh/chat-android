package vn.unima.demoapps.chat.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.unima.demoapps.chat.abstracts.NMActivity;
import vn.unima.demoapps.chat.abstracts.NMFragment;
import vn.unima.demoapps.chat.activities.HomeActivity;
import vn.unima.demoapps.chat.adapters.ChatAdapter;
import vn.unima.demoapps.chat.objects.ChatMessage;
import vn.unima.demoapps.chat.utils.AppUtils;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class ChatFragment extends NMFragment {
    private ChatAdapter adapter;
    private DatabaseReference database;
    private EditText etSendMessage;
    private RecyclerView recyclerView;
    private List<ChatMessage> messages;

    @Override
    protected void initData(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        messages = new ArrayList<>();
        adapter = new ChatAdapter();
        database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(vn.unima.demoapps.chat.R.layout.fragment_chat, container, false);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(vn.unima.demoapps.chat.R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        etSendMessage = (EditText) view.findViewById(vn.unima.demoapps.chat.R.id.et_send_message);

        view.findViewById(vn.unima.demoapps.chat.R.id.bt_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getListMessages();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(vn.unima.demoapps.chat.R.menu.menu_sign_out, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == vn.unima.demoapps.chat.R.id.menu_sign_out) {
            signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        goToHomeActivity();
    }

    private void goToHomeActivity() {
        NMActivity.startActivity(getContext(), HomeActivity.class, null);
        getActivity().finish();
    }

    private void getListMessages() {
        database.child(AppUtils.ARG_ROOMS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    try {
                        ChatMessage chatMessage = noteDataSnapshot.getValue(ChatMessage.class);
                        messages.add(chatMessage);
                    } catch (DatabaseException ignored) {

                    }
                }
                refreshAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private void refreshAdapter() {
        adapter.setData(messages);
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(messages.size() - 1);
    }

    private void sendMessage() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) return;

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUid(database.child(AppUtils.ARG_ROOMS).push().getKey());
        chatMessage.setUserName(!TextUtils.isEmpty(firebaseUser.getDisplayName()) ? firebaseUser.getDisplayName() : firebaseUser.getEmail());
        chatMessage.setMessage(etSendMessage.getText().toString());
        chatMessage.setTimeStamp(System.currentTimeMillis());
        database.child(AppUtils.ARG_ROOMS).child(chatMessage.getUid()).setValue(chatMessage);

        etSendMessage.setText(null);
    }
}
