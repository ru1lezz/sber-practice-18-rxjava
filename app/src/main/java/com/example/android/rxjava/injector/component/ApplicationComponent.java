package com.example.android.rxjava.injector.component;


import com.example.android.rxjava.domain.SharedPrefRepository;
import com.example.android.rxjava.domain.WeatherRepository;
import com.example.android.rxjava.injector.module.ApplicationModule;
import com.example.android.rxjava.presentation.view.detailedweather.DetailedWeatherActivity;
import com.example.android.rxjava.presentation.view.weatherlist.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(DetailedWeatherActivity detailedWeatherActivity);

    WeatherRepository getForecastRepository();

    SharedPrefRepository getSharedPrefRepository();
}
