package com.example.milan.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Milan on 2016.01.06..
 */

/***
 * adatok lekérése
 */
public class Item implements JSONPopulator {

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }
    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
