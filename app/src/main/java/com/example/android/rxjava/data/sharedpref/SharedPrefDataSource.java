package com.example.android.rxjava.data.sharedpref;

import com.example.android.rxjava.domain.model.DomainSharedPref;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface SharedPrefDataSource {
    Single<DomainSharedPref> get();

    Completable set(DomainSharedPref sharedPref);
}
