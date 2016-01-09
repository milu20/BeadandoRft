package com.example.milan.weatherapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milan.weatherapp.data.Channel;
import com.example.milan.weatherapp.data.Item;
import com.example.milan.weatherapp.data.Wind;
import com.example.milan.weatherapp.services.WeatherServiceCallback;
import com.example.milan.weatherapp.services.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView speedTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);
        speedTextView = (TextView)findViewById(R.id.speedTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Budapest, HU");

    }

    @Override
    public void serviceSucces(Channel channel) {
        dialog.hide();

        Wind wind = channel.getWind();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawble = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawble);

        speedTextView.setText(wind.getSpeed() + channel.getWind().getSpeed());

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0 " + channel.getUnits().getTemperature());

        conditionTextView.setText(item.getCondition().getDescription());

        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
