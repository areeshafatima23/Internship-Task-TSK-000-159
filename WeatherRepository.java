package com.example.weatherapp;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private WeatherService weatherService;

    public WeatherRepository(Context context) {
        weatherService = RetrofitClient.getClient().create(WeatherService.class);
    }

    public LiveData<WeatherResponse> getWeather(String cityName, String apiKey) {
        MutableLiveData<WeatherResponse> data = new MutableLiveData<>();
        weatherService.getWeather(cityName, apiKey, "metric").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Handle failure
            }
        });
        return data;
    }

    public LiveData<GeoResponse> getGeoLocation(String cityName, int limit, String apiKey) {
        MutableLiveData<GeoResponse> data = new MutableLiveData<>();
        weatherService.getGeoLocation(cityName, limit, apiKey).enqueue(new Callback<GeoResponse>() {
            @Override
            public void onResponse(Call<GeoResponse> call, Response<GeoResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GeoResponse> call, Throwable t) {
                // Handle failure
            }
        });
        return data;
    }
}
