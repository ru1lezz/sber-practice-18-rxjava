package com.example.android.rxjava.data.sharedpref;

import com.example.android.rxjava.domain.model.DomainSharedPref;
import com.example.android.rxjava.domain.SharedPrefRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class SharedPrefRepositoryImpl implements SharedPrefRepository {
    private final SharedPrefDataSource sharedPrefDataSource;

    @Inject
    public SharedPrefRepositoryImpl(SharedPrefDataSource sharedPrefDataStorage) {
        this.sharedPrefDataSource = sharedPrefDataStorage;
    }

    @Override
    public Single<DomainSharedPref> getSharedPref() {
        return sharedPrefDataSource.get();
    }

    @Override
    public Completable setSharedPref(DomainSharedPref domainSharedPref) {
        return sharedPrefDataSource.set(domainSharedPref);
    }
}
