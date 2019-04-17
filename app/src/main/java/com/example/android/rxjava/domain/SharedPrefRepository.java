package com.example.android.rxjava.domain;

import com.example.android.rxjava.domain.model.DomainSharedPref;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface SharedPrefRepository {
    Single<DomainSharedPref> getSharedPref();

    Completable setSharedPref(DomainSharedPref domainSharedPref);
}
