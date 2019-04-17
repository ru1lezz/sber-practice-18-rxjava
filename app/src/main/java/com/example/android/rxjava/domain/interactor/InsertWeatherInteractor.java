package com.example.android.rxjava.domain.interactor;

import com.example.android.rxjava.domain.WeatherRepository;
import com.example.android.rxjava.domain.model.DomainWeather;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

public class InsertWeatherInteractor {
    private WeatherRepository mRepository;

    @Inject
    public InsertWeatherInteractor(WeatherRepository mRepository) {
        this.mRepository = mRepository;
    }

    public Completable execute(List<DomainWeather> list) {
        return mRepository.insert(list);
    }
}
