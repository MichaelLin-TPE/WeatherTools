package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EarthquakeObject implements Serializable {
    @SerializedName("records")
    private EarthquakeRecords records;

    public EarthquakeRecords getRecords() {
        return records;
    }

    public void setRecords(EarthquakeRecords records) {
        this.records = records;
    }
}
