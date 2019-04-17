package com.example.android.rxjava.injector.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.android.rxjava.data.sharedpref.SharedPrefDataSource;
import com.example.android.rxjava.data.sharedpref.SharedPrefDataSourceImpl;
import com.example.android.rxjava.data.weather.LocalDataSource;
import com.example.android.rxjava.data.weather.RemoteDataSource;
import com.example.android.rxjava.data.weather.WeatherRepositoryImpl;
import com.example.android.rxjava.data.weather.db.LocalDataSourceImpl;
import com.example.android.rxjava.data.weather.db.room.WeatherDatabase;
import com.example.android.rxjava.data.weather.network.RemoteDataSourceImpl;
import com.example.android.rxjava.data.weather.network.WeatherService;
import com.example.android.rxjava.data.sharedpref.SharedPrefRepositoryImpl;
import com.example.android.rxjava.domain.SharedPrefRepository;
import com.example.android.rxjava.domain.WeatherRepository;
import com.example.android.rxjava.domain.interactor.GetSharedPrefInteractor;
import com.example.android.rxjava.domain.interactor.SetSharedPrefInteractor;
import com.example.android.rxjava.presentation.WeatherApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final WeatherApplication weatherApplication;


    public ApplicationModule(WeatherApplication weatherApplication) {
        this.weatherApplication = weatherApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return weatherApplication;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    SharedPrefRepository provideSharedPrefRepository(SharedPrefRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    RemoteDataSource provideRemoteDataSource(RemoteDataSourceImpl dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    LocalDataSource provideLocalDataSource(LocalDataSourceImpl dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    SharedPrefDataSource provdeSharedPrefDataSource(SharedPrefDataSourceImpl dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    WeatherDatabase provideWeatherDatabase(Context context) {
        return Room.databaseBuilder(context, WeatherDatabase.class, "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService() {
        final String BASE_URL = "http://api.apixu.com/v1/";

        final Gson gson = new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(WeatherService.class);
    }


    @Provides
    @Singleton
    Resources getResources(Context context) { return context.getResources(); }

    @Provides
    @Singleton
    RequestManager provideGlide() { return Glide.with(provideContext()); }
}

