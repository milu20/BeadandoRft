package com.example.milan.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Milan on 2016.01.09..
 */

/***
 * adatok lekérése
 */
public class Wind implements JSONPopulator {
    private double speed;

    public double getSpeed() {
        return speed;
    }

    @Override
    public void populate(JSONObject data) {
        speed=data.optDouble("speed");
    }
}
