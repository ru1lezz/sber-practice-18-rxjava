package com.example.android.rxjava.domain.interactor;

import android.util.Log;

import com.example.android.rxjava.domain.WeatherRepository;
import com.example.android.rxjava.domain.model.DomainWeather;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetWeatherListInteractor {
    private final WeatherRepository mRepository;

    @Inject
    public GetWeatherListInteractor(WeatherRepository repository) {
        mRepository = repository;
    }

    public Observable<List<DomainWeather>> execute(String city, String days) {
        return mRepository.getRemoteWeatherList(city, days);
    }

}
