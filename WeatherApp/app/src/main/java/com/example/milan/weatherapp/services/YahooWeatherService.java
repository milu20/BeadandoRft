package com.example.milan.weatherapp.services;

import android.graphics.AvoidXfermode;
import android.net.Uri;
import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Milan on 2016.01.06..
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private  String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(final String location){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", location);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=&format=json", Uri.encode(YQL));

                URLConnection connection = url.openConnection();
                try {
                    URL url = new URL(endpoint);
                } catch (MalformedURLException e) {
                    error = e;
                    return null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute(location);

    }
}
