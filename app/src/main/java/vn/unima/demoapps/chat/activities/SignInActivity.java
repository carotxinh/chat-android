package vn.unima.demoapps.chat.activities;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import vn.unima.demoapps.chat.abstracts.NMActivityFragment;
import vn.unima.demoapps.chat.fragments.SignInFragment;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class SignInActivity extends NMActivityFragment {
    @NonNull
    @Override
    protected Fragment initFragment() {
        return new SignInFragment();
    }

    @Override
    protected void initActionBar(@NonNull ActionBar actionBar) {
        super.initActionBar(actionBar);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
