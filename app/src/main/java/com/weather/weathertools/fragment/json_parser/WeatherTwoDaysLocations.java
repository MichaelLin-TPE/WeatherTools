package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherTwoDaysLocations implements Serializable {
    @SerializedName("location")
    private ArrayList<WeatherTwoDaysLocation> location;

    public ArrayList<WeatherTwoDaysLocation> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<WeatherTwoDaysLocation> location) {
        this.location = location;
    }
}
