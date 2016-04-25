package com.witbit.sherlock.oneapp;

import android.app.Application;

import com.zhy.changeskin.SkinManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //换肤
        SkinManager.getInstance().init(this);

    }
}
