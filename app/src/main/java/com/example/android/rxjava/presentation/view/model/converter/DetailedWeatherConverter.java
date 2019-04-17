package com.example.android.rxjava.presentation.view.model.converter;

import com.example.android.rxjava.domain.Converter;
import com.example.android.rxjava.domain.model.DomainWeather;
import com.example.android.rxjava.presentation.view.model.DetailedWeather;

public class DetailedWeatherConverter extends Converter<DomainWeather, DetailedWeather> {
    @Override
    public DetailedWeather convertTo(DomainWeather domainWeather) {
        DetailedWeather to = new DetailedWeather();
        to.setAvgHumidity(domainWeather.getAvgHumidity());
        to.setDate(domainWeather.getDate());
        to.setEpoch(domainWeather.getEpoch());
        to.setMaxTemp(domainWeather.getMaxTemp());
        to.setMaxWind(domainWeather.getMaxWind());
        to.setMinTemp(domainWeather.getMinTemp());
        to.setText(domainWeather.getText());
        to.setUrlIcon(domainWeather.getIconUrl());
        return to;
    }

    @Override
    public DomainWeather convertFrom(DetailedWeather detailedWeather) {
        return null;
    }
}
