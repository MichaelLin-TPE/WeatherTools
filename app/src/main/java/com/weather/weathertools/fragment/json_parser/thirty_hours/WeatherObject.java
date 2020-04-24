package com.weather.weathertools.fragment.json_parser.thirty_hours;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherObject implements Serializable {
    @SerializedName("records")
    private WeatherRecords records;

    public WeatherRecords getRecords() {
        return records;
    }

    public void setRecords(WeatherRecords records) {
        this.records = records;
    }
}
