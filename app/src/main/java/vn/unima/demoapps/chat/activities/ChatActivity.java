package vn.unima.demoapps.chat.activities;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import vn.unima.demoapps.chat.abstracts.NMActivityFragment;
import vn.unima.demoapps.chat.fragments.ChatFragment;

public class ChatActivity extends NMActivityFragment {

    @NonNull
    @Override
    protected Fragment initFragment() {
        return new ChatFragment();
    }
}
