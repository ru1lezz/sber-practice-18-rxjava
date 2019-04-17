package com.example.android.rxjava.presentation.view.model.converter;

import com.example.android.rxjava.domain.Converter;
import com.example.android.rxjava.domain.model.DomainSharedPref;
import com.example.android.rxjava.presentation.view.model.SharedPref;

public class DomainSharedPrefConverter extends Converter<SharedPref, DomainSharedPref> {

    @Override
    public DomainSharedPref convertTo(SharedPref sharedPref) {
        DomainSharedPref to = new DomainSharedPref();
        to.setDays(sharedPref.getDays());
        to.setCity(sharedPref.getCity());
        return to;
    }

    @Override
    public SharedPref convertFrom(DomainSharedPref domainSharedPref) {
        SharedPref from = new SharedPref();
        from.setCity(domainSharedPref.getCity());
        from.setDays(domainSharedPref.getDays());
        return from;
    }
}