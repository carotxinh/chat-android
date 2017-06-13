package vn.unima.demoapps.chat.abstracts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import vn.unima.demoapps.chat.utils.AppUtils;


/**
 * Striker
 * Created by vinhn_000 on 2/25/2015.
 * Copyright 2015 Nikmesoft Company, Ltd. All rights reserved.
 */
public abstract class NMActivity extends AppCompatActivity {

    public static void startActivity(@NonNull Context context, @NonNull Class<? extends Activity> cls, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int requestCode, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int requestCode, @Nullable Bundle bundle) {
        Intent intent = new Intent(fragment.getContext(), cls);
        if (bundle != null) intent.putExtras(bundle);
        fragment.startActivityForResult(intent, requestCode);
    }

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initRootView(Bundle savedInstanceState);

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void loadData(Bundle savedInstanceState);

    protected void initActionBar(@NonNull ActionBar actionBar) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onCreate(savedInstanceState);
        initRootView(savedInstanceState);
        initUI(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            initActionBar(actionBar);
        }
        loadData(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getSupportActionBar() != null) {
                if ((getSupportActionBar().getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP) > 0) {
                    AppUtils.navigateUp(this);
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public NMActivity getContext() {
        return this;
    }
}
