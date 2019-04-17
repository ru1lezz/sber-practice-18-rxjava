package com.example.android.rxjava.data.weather.network.model;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("forecast")
    private WeatherList weatherList;

    public WeatherList getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(WeatherList weatherList) {
        this.weatherList = weatherList;
    }
}
