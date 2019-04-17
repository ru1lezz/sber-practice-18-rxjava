package com.example.android.rxjava.data.weather;

import com.example.android.rxjava.domain.model.DomainWeather;
import com.example.android.rxjava.domain.WeatherRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

@Singleton
public class WeatherRepositoryImpl implements WeatherRepository {

    private RemoteDataSource mRemoteDataSource;
    private LocalDataSource mLocalDataSource;

    @Inject
    public WeatherRepositoryImpl(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<List<DomainWeather>> getRemoteWeatherList(String city, String days) {
        return mRemoteDataSource.getWeatherList(city, days)
                .map(weatherDtoList -> new DomainWeatherConverter().convertToAll(weatherDtoList))
                .flatMap(domainWeatherList -> Observable.just(domainWeatherList)
                        .doOnNext(domainWeatherList1 -> insert(domainWeatherList1).subscribe()));
    }

    @Override
    public Single<DomainWeather> getWeatherLocal(String city, long epoch) {
        return mLocalDataSource.getWeather(city, epoch)
                .map(weatherDto -> new DomainWeatherConverter().convertTo(weatherDto));
    }

    @Override
    public Completable insert(List<DomainWeather> domainWeatherList) {
        return mLocalDataSource.insert(new DomainWeatherConverter().convertFromAll(domainWeatherList));
    }
}
