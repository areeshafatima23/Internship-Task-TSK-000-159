package com.example.weatherapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherEntity weather);

    @Query("SELECT * FROM weather WHERE city = :city")
    LiveData<WeatherEntity> getWeatherByCity(String city);
}
