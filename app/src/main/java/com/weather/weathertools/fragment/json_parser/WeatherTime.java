package com.weather.weathertools.fragment.json_parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherTime implements Serializable {
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("parameter")
    private WeatherParameter parameter;

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

    public WeatherParameter getParameter() {
        return parameter;
    }

    public void setParameter(WeatherParameter parameter) {
        this.parameter = parameter;
    }
}
