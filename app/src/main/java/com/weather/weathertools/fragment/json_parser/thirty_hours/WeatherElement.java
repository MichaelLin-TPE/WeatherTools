package com.weather.weathertools.fragment.json_parser.thirty_hours;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherElement implements Serializable {
    @SerializedName("elementName")
    private String elementName;
    @SerializedName("time")
    private ArrayList<WeatherTime> time;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public ArrayList<WeatherTime> getTime() {
        return time;
    }

    public void setTime(ArrayList<WeatherTime> time) {
        this.time = time;
    }
}
