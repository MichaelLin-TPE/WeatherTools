package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherLocation implements Serializable {

    @SerializedName("locationName")
    private String locationName;
    @SerializedName("weatherElement")
    private ArrayList<WeatherElement> weatherElement;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<WeatherElement> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(ArrayList<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
    }
}
