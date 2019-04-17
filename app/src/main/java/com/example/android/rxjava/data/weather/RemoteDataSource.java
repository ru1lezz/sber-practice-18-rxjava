package com.example.android.rxjava.data.weather;

import com.example.android.rxjava.data.WeatherDto;

import java.util.List;

import io.reactivex.Observable;

public interface RemoteDataSource {
    Observable<List<WeatherDto>> getWeatherList(String city, String days);
}
