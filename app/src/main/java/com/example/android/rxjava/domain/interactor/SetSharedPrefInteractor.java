package com.example.android.rxjava.domain.interactor;

import android.util.Log;

import com.example.android.rxjava.domain.SharedPrefRepository;
import com.example.android.rxjava.domain.model.DomainSharedPref;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SetSharedPrefInteractor {
    private final SharedPrefRepository repository;

    @Inject
    public SetSharedPrefInteractor(SharedPrefRepository repository) {
        this.repository = repository;
    }

    public Completable execute(DomainSharedPref domainSharedPref) {
        return repository.setSharedPref(domainSharedPref);
    }
}
