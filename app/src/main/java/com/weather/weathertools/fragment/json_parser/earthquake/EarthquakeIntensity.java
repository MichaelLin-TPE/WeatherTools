package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class EarthquakeIntensity implements Serializable {
    @SerializedName("shakingArea")
    private ArrayList<EarthquakeShakingArea> shakingArea;

    public ArrayList<EarthquakeShakingArea> getShakingArea() {
        return shakingArea;
    }

    public void setShakingArea(ArrayList<EarthquakeShakingArea> shakingArea) {
        this.shakingArea = shakingArea;
    }
}
