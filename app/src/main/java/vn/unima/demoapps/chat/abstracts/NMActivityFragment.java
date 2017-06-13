package vn.unima.demoapps.chat.abstracts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import vn.unima.demoapps.chat.R;

/**
 * MyEvolution
 * Created by vinhn_000 on 11/12/2015.
 * Copyright 2015 Nikmesoft Company, Ltd. All rights reserved.
 */
public abstract class NMActivityFragment extends NMActivity {
    private Fragment fragment;

    @Override
    protected void initData(Bundle savedInstanceState) {
        fragment = initFragment();
        fragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fragment);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @NonNull
    protected abstract Fragment initFragment();

    public Fragment getFragment() {
        return fragment;
    }
}
