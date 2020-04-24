package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherTwoDaysElementValue implements Serializable {
    @SerializedName("value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
