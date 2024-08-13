package com.example.weatherapp;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WeatherViewModel extends AndroidViewModel {
    private WeatherRepository weatherRepository;

    public WeatherViewModel(Application application, WeatherRepository repository) {
        super(application);
        this.weatherRepository = repository;
    }

    public LiveData<WeatherResponse> getWeather(String cityName, String apiKey) {
        return weatherRepository.getWeather(cityName, apiKey);
    }
}
