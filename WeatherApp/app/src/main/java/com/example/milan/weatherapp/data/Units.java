package com.example.milan.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Milan on 2016.01.06..
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.getString("temperature");
    }
}
