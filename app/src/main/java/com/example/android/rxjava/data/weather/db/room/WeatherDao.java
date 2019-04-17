package com.example.android.rxjava.data.weather.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.android.rxjava.data.weather.db.model.WeatherLocal;

import java.util.List;
import java.util.Observable;

import io.reactivex.Single;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM WeatherLocal WHERE WeatherLocal.city = :city and WeatherLocal.epoch = :epoch")
    Single<WeatherLocal> getWeather(String city, long epoch);

    @Query("SELECT * FROM WeatherLocal")
    Single<List<WeatherLocal>> getWeatherList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<WeatherLocal> weatherLocalList);
}

