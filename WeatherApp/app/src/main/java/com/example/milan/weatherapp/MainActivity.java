package com.example.milan.weatherapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milan.weatherapp.data.Channel;
import com.example.milan.weatherapp.data.Item;
import com.example.milan.weatherapp.data.Wind;
import com.example.milan.weatherapp.data.Wind;
import com.example.milan.weatherapp.services.WeatherServiceCallback;
import com.example.milan.weatherapp.services.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback, View.OnClickListener {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView speedTextView;
    private EditText et_location;

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
        et_location = (EditText)findViewById(R.id.editText);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather(et_location.getText().toString());
    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Wind wind = channel.getWind();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        speedTextView.setText(channel.getWind().getSpeed() + channel.getUnits().getSpeed());
        //Toast.makeText(this,channel.getWind().getSpeed() + channel.getUnits().getSpeed(),Toast.LENGTH_LONG).show();
        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0 " + channel.getUnits().getTemperature());

        conditionTextView.setText(item.getCondition().getDescription());

        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button:
            {
                dialog.show();
                service.refreshWeather(et_location.getText().toString());
                break;
            }
        }
    }
}
