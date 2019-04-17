package com.example.android.rxjava.data.weather.network;

import com.example.android.rxjava.data.weather.network.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET(value = "forecast.json")
    Observable<WeatherResponse> getWeatherList(@Query("key") String key, @Query("q") String name, @Query("days") String days, @Query("lang") String lang);
}
