package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EarthquakeDepth implements Serializable {
    @SerializedName("value")
    private String value;
    @SerializedName("unit")
    private String unit;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
