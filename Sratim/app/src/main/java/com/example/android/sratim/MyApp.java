package com.example.android.sratim;

/**
 * Created by Administrator on 15/03/2018.
 */

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}