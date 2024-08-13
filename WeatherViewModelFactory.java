package com.example.weatherapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.weatherapp.WeatherRepository;

public class WeatherViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Application application;
    private final WeatherRepository weatherRepository;

    public WeatherViewModelFactory(Application application, WeatherRepository repository) {
        this.application = application;
        this.weatherRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WeatherViewModel.class)) {
            return (T) new WeatherViewModel(application, weatherRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

