package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherTwoDaysElement implements Serializable {

    @SerializedName("elementName")
    private String elementName;
    @SerializedName("time")
    private ArrayList<WeatherTwoDaysTime> time;

    public String getElemtNmae() {
        return elementName;
    }

    public void setElemtNmae(String elemtNmae) {
        this.elementName = elemtNmae;
    }

    public ArrayList<WeatherTwoDaysTime> getTime() {
        return time;
    }

    public void setTime(ArrayList<WeatherTwoDaysTime> time) {
        this.time = time;
    }
}
