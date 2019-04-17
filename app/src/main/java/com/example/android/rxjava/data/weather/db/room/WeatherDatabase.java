package com.example.android.rxjava.data.weather.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.android.rxjava.data.weather.db.model.WeatherLocal;

@Database(entities = WeatherLocal.class, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao getWeatherDao();
}
