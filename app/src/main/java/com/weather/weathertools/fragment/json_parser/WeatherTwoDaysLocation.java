package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherTwoDaysLocation implements Serializable {
    @SerializedName("locationName")
    private String locationName;
    @SerializedName("weatherElement")
    private ArrayList<WeatherTwoDaysElement> weatherElement;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<WeatherTwoDaysElement> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(ArrayList<WeatherTwoDaysElement> weatherElement) {
        this.weatherElement = weatherElement;
    }
}
