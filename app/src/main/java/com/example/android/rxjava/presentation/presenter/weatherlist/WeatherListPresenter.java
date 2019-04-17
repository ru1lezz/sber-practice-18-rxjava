package com.example.android.rxjava.presentation.presenter.weatherlist;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.android.rxjava.R;
import com.example.android.rxjava.domain.SharedPrefRepository;
import com.example.android.rxjava.domain.interactor.GetSharedPrefInteractor;
import com.example.android.rxjava.domain.interactor.GetWeatherListInteractor;
import com.example.android.rxjava.domain.interactor.InsertWeatherInteractor;
import com.example.android.rxjava.domain.interactor.SetSharedPrefInteractor;
import com.example.android.rxjava.domain.model.DomainSharedPref;
import com.example.android.rxjava.domain.model.DomainWeather;
import com.example.android.rxjava.presentation.view.model.converter.DomainSharedPrefConverter;
import com.example.android.rxjava.presentation.view.model.converter.WeatherConverter;
import com.example.android.rxjava.presentation.presenter.BasePresenter;
import com.example.android.rxjava.presentation.view.model.SharedPref;
import com.example.android.rxjava.presentation.view.model.Weather;
import com.example.android.rxjava.presentation.view.weatherlist.MainView;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherListPresenter implements BasePresenter {

    private Resources mResources;
    private MainView mView;
    private GetWeatherListInteractor mGetWeatherListInteractor;
    private GetSharedPrefInteractor mGetSharedPrefInteractor;
    private InsertWeatherInteractor mInsertWeatherInteractor;
    private SetSharedPrefInteractor mSetSharedPrefInteractor;
    private SharedPref mSharedPref;

    @Inject
    public WeatherListPresenter(GetWeatherListInteractor getWeatherListInteractor, GetSharedPrefInteractor getSharedPrefInteractor, InsertWeatherInteractor insertWeatherInteractor, SetSharedPrefInteractor setSharedPrefInteractor,  Resources resources) {
        mGetWeatherListInteractor = getWeatherListInteractor;
        mGetSharedPrefInteractor = getSharedPrefInteractor;
        mInsertWeatherInteractor = insertWeatherInteractor;
        mSetSharedPrefInteractor = setSharedPrefInteractor;
        mResources = resources;
    }

    public void setView(MainView view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mGetSharedPrefInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::initSharedPref);
    }

    public void onResume() {

    }

    private void initSharedPref(DomainSharedPref domainSharedPref) {
        mSharedPref = new DomainSharedPrefConverter().convertFrom(domainSharedPref);
        mView.setCity(mSharedPref.getCity());
        mView.setDays(mSharedPref.getDays() - 1);
    }


    @SuppressLint("CheckResult")
    @Override
    public void onLoad() {
        mGetWeatherListInteractor.execute(mSharedPref.getCity(), String.valueOf(mSharedPref.getDays()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(domainWeathers -> new WeatherConverter().convertToAll(domainWeathers))
                .subscribe(weathers -> mView.displayWeatherList(weathers));
        mSetSharedPrefInteractor.execute(new DomainSharedPrefConverter().convertTo(mSharedPref))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    public void onSpinnerItemSelected(int days) {
        mSharedPref.setDays(days);
    }

    public void onCityEditTextChanged(String city) {
        mSharedPref.setCity(city);
    }

}