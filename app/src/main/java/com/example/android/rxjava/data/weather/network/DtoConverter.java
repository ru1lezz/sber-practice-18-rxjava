package com.example.android.rxjava.data.weather.network;

import com.example.android.rxjava.data.WeatherDto;
import com.example.android.rxjava.data.weather.network.model.RemoteWeather;
import com.example.android.rxjava.domain.Converter;

public class DtoConverter extends Converter<RemoteWeather, WeatherDto> {
    private final String city;

    public DtoConverter(String city) {
        this.city = city;
    }

    @Override
    public WeatherDto convertTo(RemoteWeather from) {
        WeatherDto to = new WeatherDto();
        to.setCity(city);
        to.setEpoch(from.getEpoch());
        to.setDate(from.getDate());
        to.setAvgHumidity(from.getDay().getAvgHumidity());
        to.setIconUrl(from.getDay().getCondition().getIconUrl());
        to.setMaxTemp(from.getDay().getMaxTemp());
        to.setMinTemp(from.getDay().getMinTemp());
        to.setMaxWind(from.getDay().getMaxWind());
        to.setText(from.getDay().getCondition().getText());
        return to;
    }

    @Override
    public RemoteWeather convertFrom(WeatherDto to) {
        return null;
    }
}