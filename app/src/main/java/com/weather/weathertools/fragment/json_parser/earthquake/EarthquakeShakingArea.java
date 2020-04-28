package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class EarthquakeShakingArea implements Serializable {
    @SerializedName("areaDesc")
    private String areaDesc;
    @SerializedName("areaName")
    private String areaName;
    @SerializedName("infoStatus")
    private String infoStatus;
    @SerializedName("areaIntensity")
    private EarthquakeAreaIntensity areaIntensity;
    @SerializedName("eqStation")
    private ArrayList<EarthquakeEqStation> eqStation;

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public EarthquakeAreaIntensity getAreaIntensity() {
        return areaIntensity;
    }

    public void setAreaIntensity(EarthquakeAreaIntensity areaIntensity) {
        this.areaIntensity = areaIntensity;
    }

    public ArrayList<EarthquakeEqStation> getEqStation() {
        return eqStation;
    }

    public void setEqStation(ArrayList<EarthquakeEqStation> eqStation) {
        this.eqStation = eqStation;
    }
}
