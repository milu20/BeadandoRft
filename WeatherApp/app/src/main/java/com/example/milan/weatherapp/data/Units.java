package com.example.milan.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Milan on 2016.01.06..
 */

/***
 * adatok lekérése
 */
public class Units implements JSONPopulator {
    private String temperature;
    private String speed;

    public String getSpeed(){
        return speed;
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
        speed = data.optString("speed");
    }
}
