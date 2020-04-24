package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherBegin implements Serializable {
    @SerializedName("records")
    private WeatherTwoDaysRecords records;

    public WeatherTwoDaysRecords getRecords() {
        return records;
    }

    public void setRecords(WeatherTwoDaysRecords records) {
        this.records = records;
    }
}
