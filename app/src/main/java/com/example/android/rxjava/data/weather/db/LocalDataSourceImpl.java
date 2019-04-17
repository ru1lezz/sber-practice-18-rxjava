package com.example.android.rxjava.data.weather.db;

import android.util.Log;

import com.example.android.rxjava.data.WeatherDto;
import com.example.android.rxjava.data.weather.LocalDataSource;
import com.example.android.rxjava.data.weather.db.model.WeatherLocal;
import com.example.android.rxjava.data.weather.db.room.WeatherDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LocalDataSourceImpl implements LocalDataSource {

    private final WeatherDatabase database;

    @Inject
    public LocalDataSourceImpl(WeatherDatabase database) {
        this.database = database;
    }

    @Override
    public Single<WeatherDto> getWeather(String city, long epoch) {
        return database.getWeatherDao().getWeather(city, epoch)
                .map(weatherLocal ->  new DbDtoConverter().convertTo(weatherLocal));
    }

    @Override
    public Completable insert(List<WeatherDto> weatherDtoList) {
        List<WeatherLocal> list = new DbDtoConverter().convertFromAll(weatherDtoList);
        return Completable.fromAction(() -> database.getWeatherDao().insert(list));
    }
}
