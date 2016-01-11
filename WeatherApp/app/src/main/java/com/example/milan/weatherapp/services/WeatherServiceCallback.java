package com.example.milan.weatherapp.services;

import com.example.milan.weatherapp.data.Channel;

/**
 * Created by Milan on 2016.01.06..
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
