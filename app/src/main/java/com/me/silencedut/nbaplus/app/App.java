package com.me.silencedut.nbaplus.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import im.fir.sdk.FIR;

/**
 * Created by SilenceDut on 2015/11/28.
 */
public class App extends Application {
    private static Context sContext;
    private RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        FIR.init(this);
        mRefWatcher = LeakCanary.install(this);
        sContext = getApplicationContext();
        AppService.getInstance().initService();
    }
    public static Context getContext() {
        return sContext;
    }

}
