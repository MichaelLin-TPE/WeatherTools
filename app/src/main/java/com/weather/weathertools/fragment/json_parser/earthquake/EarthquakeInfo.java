package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EarthquakeInfo implements Serializable {
    @SerializedName("originTime")
    private String originTime;
    @SerializedName("source")
    private String source;
    @SerializedName("depth")
    private EarthquakeDepth depth;
    @SerializedName("epiCenter")
    private EarthquakeEpiCenter epiCenter;
    @SerializedName("magnitude")
    private EarthquakeMagnitude magnitude;

    public String getOriginTime() {
        return originTime;
    }

    public void setOriginTime(String originTime) {
        this.originTime = originTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public EarthquakeDepth getDepth() {
        return depth;
    }

    public void setDepth(EarthquakeDepth depth) {
        this.depth = depth;
    }

    public EarthquakeEpiCenter getEpiCenter() {
        return epiCenter;
    }

    public void setEpiCenter(EarthquakeEpiCenter epiCenter) {
        this.epiCenter = epiCenter;
    }
}
