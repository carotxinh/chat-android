package vn.unima.demoapps.chat.activities;

import android.os.Bundle;

import vn.unima.demoapps.chat.abstracts.NMActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class LauncherActivity extends NMActivity {
    @Override
    protected void initData(Bundle savedInstanceState) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            goToHomeActivity();
        } else {
            goToChatActivity();
        }
    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {

    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    private void goToChatActivity() {
        NMActivity.startActivity(this, ChatActivity.class, null);
        finish();
    }

    private void goToHomeActivity() {
        NMActivity.startActivity(this, HomeActivity.class, null);
        finish();
    }

}
