package com.example.android.rxjava.domain.interactor;

import com.example.android.rxjava.domain.SharedPrefRepository;
import com.example.android.rxjava.domain.model.DomainSharedPref;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetSharedPrefInteractor {
    private final SharedPrefRepository mRepository;

    @Inject
    public GetSharedPrefInteractor(SharedPrefRepository repository) {
        mRepository = repository;
    }

    public Single<DomainSharedPref> execute() { return mRepository.getSharedPref();}
}
