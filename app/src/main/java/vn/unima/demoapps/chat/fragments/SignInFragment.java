package vn.unima.demoapps.chat.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import vn.unima.demoapps.chat.abstracts.NMActivity;
import vn.unima.demoapps.chat.abstracts.NMFragment;
import vn.unima.demoapps.chat.activities.ChatActivity;
import vn.unima.demoapps.chat.utils.AppUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class SignInFragment extends NMFragment {
    private FirebaseAuth mAuth;

    private EditText edMail;
    private EditText edPassword;
    private ProgressDialog loadingDialog;

    @Override
    protected void initData(Bundle savedInstanceState) {
        loadingDialog = new ProgressDialog(getContext());
        loadingDialog.setMessage(getContext().getString(vn.unima.demoapps.chat.R.string.message_please_wait));
        loadingDialog.setCancelable(false);
        loadingDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(vn.unima.demoapps.chat.R.layout.fragment_sign_in, container, false);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        edMail = (EditText) view.findViewById(vn.unima.demoapps.chat.R.id.et_email);
        edPassword = (EditText) view.findViewById(vn.unima.demoapps.chat.R.id.et_password);

        mAuth = FirebaseAuth.getInstance();

        view.findViewById(vn.unima.demoapps.chat.R.id.bt_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    signIn();
                }
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    private boolean validate() {
        if (TextUtils.isEmpty(edMail.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
            AppUtils.showMessage(getContext(), vn.unima.demoapps.chat.R.string.message_field_invalid, null, null);
            return false;
        }
        return true;
    }

    private void signIn() {
        final String mail = edMail.getText().toString();
        final String password = edPassword.getText().toString();

        loadingDialog.show();
        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        goToChatActivity();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AppUtils.showMessage(getContext(), e.getMessage(), null, null);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loadingDialog.dismiss();
                    }
                });
    }

    private void goToChatActivity() {
        NMActivity.startActivity(getContext(), ChatActivity.class, null);
    }
}
