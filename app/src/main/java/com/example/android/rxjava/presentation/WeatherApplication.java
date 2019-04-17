package com.example.android.rxjava.presentation;

import android.app.Application;

import com.example.android.rxjava.injector.component.ApplicationComponent;
import com.example.android.rxjava.injector.component.DaggerApplicationComponent;
import com.example.android.rxjava.injector.module.ApplicationModule;

public class WeatherApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() { return applicationComponent; }
}
