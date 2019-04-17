package com.example.android.rxjava.presentation.presenter.weather;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.bumptech.glide.RequestManager;
import com.example.android.rxjava.domain.interactor.GetWeatherInteractor;
import com.example.android.rxjava.domain.model.DomainWeather;
import com.example.android.rxjava.presentation.view.model.converter.DetailedWeatherConverter;
import com.example.android.rxjava.presentation.presenter.BasePresenter;
import com.example.android.rxjava.presentation.view.detailedweather.DetailedWeatherView;
import com.example.android.rxjava.presentation.view.model.DetailedWeather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter implements BasePresenter {

    private DetailedWeatherView mView;
    private ExecutorService mExecutorService;
    private Handler mHandler;
    private RequestManager mGlide;
    @Inject
    GetWeatherInteractor mInteractor;

    private String mCity;
    private long mEpoch;

    @Inject
    public WeatherPresenter(GetWeatherInteractor interactor, RequestManager glide) {
        mInteractor = interactor;
        mGlide = glide;
    }

    public void setView(DetailedWeatherView view) {
        mView = view;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public void setEpoch(long epoch) {
        mEpoch = epoch;
    }

    @Override
    public void onCreate() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @SuppressLint("CheckResult")
    @Override
    public void onResume() {
        mInteractor.setCity(mCity);
        mInteractor.setEpoch(mEpoch);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
        Date date = new Date(mEpoch * 1000);
        mInteractor.execute()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(domainWeather -> new DetailedWeatherConverter().convertTo(domainWeather))
                .subscribe(detailedWeather -> mHandler.post(() -> {
                    mView.setCity(mCity);
                    mView.setDate(sdf.format(date));
                    mView.setHighTemperature(String.valueOf(detailedWeather.getMaxTemp()));
                    mView.setLowTemperature(String.valueOf(detailedWeather.getMinTemp()));
                    mView.setHumidity(String.valueOf(detailedWeather.getAvgHumidity()));
                    mView.setWeatherDescription(detailedWeather.getText());
                    mView.setWind(String.valueOf(detailedWeather.getMaxWind()));
                    mView.setIconUrl("http:" + detailedWeather.getUrlIcon());
                }));
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
