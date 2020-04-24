package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherTwoDaysTime implements Serializable {
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("elementValue")
    private ArrayList<WeatherTwoDaysElementValue> elementValue;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<WeatherTwoDaysElementValue> getElementValue() {
        return elementValue;
    }

    public void setElementValue(ArrayList<WeatherTwoDaysElementValue> elementValue) {
        this.elementValue = elementValue;
    }
}
