package com.example.weatherapp;

import android.content.Context;
import androidx.room.Room;

public class WeatherDatabaseSingleton {
    private static WeatherDatabase instance;

    public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDatabase.class, "weather_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
