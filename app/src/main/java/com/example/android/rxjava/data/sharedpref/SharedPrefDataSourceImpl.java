package com.example.android.rxjava.data.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.rxjava.domain.model.DomainSharedPref;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SharedPrefDataSourceImpl implements SharedPrefDataSource {

    private static final String PREF_NAME = "preferences";
    private static final String CITY = "city";
    private static final String DAYS = "days";
    private static final String DEFAULT_CITY = "Moscow";
    private static final int DEFAULT_DAYS = 7;

    private final SharedPreferences mSharedPreferences;

    @Inject
    public SharedPrefDataSourceImpl(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Single<DomainSharedPref> get() {
        return Single.fromCallable(() -> {
            String city = mSharedPreferences.getString(CITY, DEFAULT_CITY);
            int days = mSharedPreferences.getInt(DAYS, DEFAULT_DAYS);
            return new DomainSharedPref(city, days);
        });
    }

    @Override
    public Completable set(DomainSharedPref sharedPref) {
        return Completable.fromAction(() ->{
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(CITY, sharedPref.getCity());
            editor.putInt(DAYS, sharedPref.getDays());
            editor.apply();
        });
    }
}
