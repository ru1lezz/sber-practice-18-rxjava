package com.example.android.rxjava.domain;

import com.example.android.rxjava.domain.model.DomainWeather;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface WeatherRepository {
    Observable<List<DomainWeather>> getRemoteWeatherList(String city, String days);
    Single<DomainWeather> getWeatherLocal(String city, long epoch);
    Completable insert(List<DomainWeather> domainWeatherList);
}
