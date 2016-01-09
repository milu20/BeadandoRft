package com.example.milan.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Milan on 2016.01.09..
 */
public class Wind implements JSONPopulator {
    private int speed;

    public int getSpeed() {
        return speed;
    }

    @Override
    public void populate(JSONObject data) {
        speed=data.optInt("speed");
    }
}
