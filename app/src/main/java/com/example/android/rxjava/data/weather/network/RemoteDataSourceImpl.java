package com.example.android.rxjava.data.weather.network;

import android.util.Log;

import com.example.android.rxjava.data.WeatherDto;
import com.example.android.rxjava.data.weather.RemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteDataSourceImpl implements RemoteDataSource {

    private static final String API_KEY = "36fb2efac1e148468fd131639190202";
    private static final String LANG = "ru";

    private final WeatherService service;

    @Inject
    public RemoteDataSourceImpl(WeatherService service) {
        this.service = service;
    }

    @Override
    public Observable<List<WeatherDto>> getWeatherList(String city, String days) {
        return service.getWeatherList(API_KEY, city, days, LANG)
                .map(weatherResponse -> new DtoConverter(city).convertToAll(weatherResponse.getWeatherList().getItems()));
    }
}
