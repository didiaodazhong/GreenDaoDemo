package com.peixing.greendaodemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by peixing on 2016/12/22.
 */

public class MyApplication extends Application {
    public static Context myApplication;

    @Override
    public void onCreate() {

        super.onCreate();

        myApplication = getApplicationContext();
        GreenDaoManager.getInstance();
    }
}
