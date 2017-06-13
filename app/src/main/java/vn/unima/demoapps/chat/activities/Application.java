package vn.unima.demoapps.chat.activities;

import com.google.firebase.database.FirebaseDatabase;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
