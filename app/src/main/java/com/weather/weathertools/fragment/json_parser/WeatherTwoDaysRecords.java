package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherTwoDaysRecords implements Serializable {
    @SerializedName("locations")
    private ArrayList<WeatherTwoDaysLocations> locations;

    public ArrayList<WeatherTwoDaysLocations> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<WeatherTwoDaysLocations> locations) {
        this.locations = locations;
    }
}
