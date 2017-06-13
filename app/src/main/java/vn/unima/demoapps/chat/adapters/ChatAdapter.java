package vn.unima.demoapps.chat.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.unima.demoapps.chat.abstracts.NMAdapter;
import vn.unima.demoapps.chat.abstracts.NMViewHolder;
import vn.unima.demoapps.chat.objects.ChatMessage;

import java.util.List;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class ChatAdapter extends NMAdapter<ChatAdapter.ChatVH> {
    private List<ChatMessage> list;

    @Override
    public ChatVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatVH(this, parent);
    }

    @Override
    public void onBindViewHolder(ChatVH holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<ChatMessage> list) {
        this.list = list;
    }

    class ChatVH extends NMViewHolder {

        private TextView tvUserName;
        private TextView tvTime;
        private TextView tvMessage;

        ChatVH(RecyclerView.Adapter<? extends NMViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, vn.unima.demoapps.chat.R.layout.list_item_chat);
        }

        @Override
        protected void initUI() {
            tvUserName = (TextView) itemView.findViewById(vn.unima.demoapps.chat.R.id.tv_user_name);
            tvTime = (TextView) itemView.findViewById(vn.unima.demoapps.chat.R.id.tv_time);
            tvMessage = (TextView) itemView.findViewById(vn.unima.demoapps.chat.R.id.tv_message);
        }

        public void setData(ChatMessage chatMessage) {
            tvUserName.setText(chatMessage.getUserName());
            tvTime.setText(DateFormat.format("HH:mm a",chatMessage.getTimeStamp()));
            tvMessage.setText(chatMessage.getMessage());
        }
    }
}
