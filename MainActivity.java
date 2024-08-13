package com.example.weatherapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private WeatherViewModel weatherViewModel;
    private EditText editTextCity;
    private TextView textViewWeather;
    private Button buttonFetch;
    private WeatherRepository weatherRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCity = findViewById(R.id.editTextCity);
        textViewWeather = findViewById(R.id.textViewWeather);
        buttonFetch = findViewById(R.id.buttonFetchWeather);

        // Create the WeatherRepository
        weatherRepository = new WeatherRepository(this);

        // Create the ViewModelFactory
        WeatherViewModelFactory factory = new WeatherViewModelFactory(getApplication(), weatherRepository);

        // Get the WeatherViewModel
        weatherViewModel = new ViewModelProvider(this, factory).get(WeatherViewModel.class);

        buttonFetch.setOnClickListener(v -> {
            String cityName = editTextCity.getText().toString();
            String apiKey = "a86a1354e0d045aa7aeb0eb97c196bde"; // Replace with your API key

            weatherViewModel.getWeather(cityName, apiKey).observe(this, new Observer<WeatherResponse>() {
                @Override
                public void onChanged(WeatherResponse weatherResponse) {
                    if (weatherResponse != null) {
                        textViewWeather.setText("City: " + weatherResponse.getName() + "\nTemp: " + weatherResponse.getMain().getTemp() + "°C");
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to fetch weather", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}



