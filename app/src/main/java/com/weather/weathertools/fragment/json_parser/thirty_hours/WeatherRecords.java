package com.weather.weathertools.fragment.json_parser.thirty_hours;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherRecords implements Serializable {

    @SerializedName("location")
    private ArrayList<WeatherLocation> location;

    public ArrayList<WeatherLocation> getLocations() {
        return location;
    }

    public void setLocations(ArrayList<WeatherLocation> locations) {
        this.location = locations;
    }
}
