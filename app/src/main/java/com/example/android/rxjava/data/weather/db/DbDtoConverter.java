package com.example.android.rxjava.data.weather.db;

import android.util.Log;

import com.example.android.rxjava.data.WeatherDto;
import com.example.android.rxjava.data.weather.db.model.WeatherLocal;
import com.example.android.rxjava.domain.Converter;

public class DbDtoConverter extends Converter<WeatherLocal, WeatherDto> {

    public DbDtoConverter() {
    }

    @Override
    public WeatherDto convertTo(WeatherLocal weatherLocal) {
        WeatherDto to = new WeatherDto();
        to.setText(weatherLocal.getText());
        to.setMinTemp(weatherLocal.getMinTemp());
        to.setMaxWind(weatherLocal.getMaxWind());
        to.setMaxTemp(weatherLocal.getMaxTemp());
        to.setIconUrl(weatherLocal.getIconUrl());
        to.setEpoch(weatherLocal.getEpoch());
        to.setDate(weatherLocal.getDate());
        to.setAvgHumidity(weatherLocal.getAvgHumidity());
        to.setCity(weatherLocal.getCity());
        return to;
    }

    @Override
    public WeatherLocal convertFrom(WeatherDto weatherDto) {
        WeatherLocal from = new WeatherLocal();
        from.setAvgHumidity(weatherDto.getAvgHumidity());
        from.setCity(weatherDto.getCity());
        Log.i(getClass().getName(), from.getCity());
        from.setDate(weatherDto.getDate());
        from.setEpoch(weatherDto.getEpoch());
        from.setIconUrl(weatherDto.getIconUrl());
        from.setMaxTemp(weatherDto.getMaxTemp());
        from.setMaxWind(weatherDto.getMaxWind());
        from.setMinTemp(weatherDto.getMinTemp());
        from.setText(weatherDto.getText());
        return from;
    }
}
