package vn.unima.demoapps.chat.activities;

import android.os.Bundle;
import android.view.View;

import vn.unima.demoapps.chat.R;
import vn.unima.demoapps.chat.abstracts.NMActivity;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class HomeActivity extends NMActivity {

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_sign_up:
                        goToSigUpActivity();
                        break;
                    case R.id.bt_log_in:
                        goToLoginActivity();
                        break;
                }
            }
        };
        findViewById(R.id.bt_sign_up).setOnClickListener(onClickListener);
        findViewById(R.id.bt_log_in).setOnClickListener(onClickListener);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    private void goToLoginActivity() {
        NMActivity.startActivity(this, SignInActivity.class, null);
    }

    private void goToSigUpActivity() {
        NMActivity.startActivity(this, SignUpActivity.class, null);
    }

}
