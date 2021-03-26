package com.developer.pinedo.publicity;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

public class MyApp extends Application {
    public void onCreate(){
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
}
