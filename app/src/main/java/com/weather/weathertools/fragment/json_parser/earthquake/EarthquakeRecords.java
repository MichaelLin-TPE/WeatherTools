package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class EarthquakeRecords implements Serializable {
    @SerializedName("earthquake")
    private ArrayList<Earthquake> earthquake;

    public ArrayList<Earthquake> getEarthquake() {
        return earthquake;
    }

    public void setEarthquake(ArrayList<Earthquake> earthquake) {
        this.earthquake = earthquake;
    }
}
