package com.example.android.rxjava.data.weather;

import com.example.android.rxjava.data.WeatherDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface LocalDataSource {
    Single<WeatherDto> getWeather(String city, long epoch);
    Completable insert(List<WeatherDto> weatherDtoList);
}
