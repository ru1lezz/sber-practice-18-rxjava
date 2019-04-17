package com.example.android.rxjava.domain.interactor;

import android.util.Log;

import com.example.android.rxjava.domain.WeatherRepository;
import com.example.android.rxjava.domain.model.DomainWeather;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class GetWeatherInteractor {
    private String city;
    private long epoch;
    private final WeatherRepository mRepository;

    @Inject
    public GetWeatherInteractor(WeatherRepository repository) {
        mRepository = repository;
    }

    public Single<DomainWeather> execute() {
        return mRepository.getWeatherLocal(city, epoch);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }
}
