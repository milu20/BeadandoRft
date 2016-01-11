package com.example.milan.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Milan on 2016.01.06..
 */
public class Channel implements JSONPopulator {
    private Item item;
    private Units units;
    private Wind wind;

    public Wind getWind(){
        return wind;
    }

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populate(JSONObject data) {

        wind = new Wind();

        wind.populate(data.optJSONObject("wind"));

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate((data.optJSONObject("item")));

    }
}
