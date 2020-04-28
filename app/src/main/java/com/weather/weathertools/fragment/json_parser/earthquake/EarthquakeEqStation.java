package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EarthquakeEqStation implements Serializable {
    @SerializedName("stationName")
    private String stationName;
    @SerializedName("stationCode")
    private String stationCode;
    @SerializedName("infoStatus")
    private String infoStatus;
    @SerializedName("waveImageURI")
    private String waveImageUri;

    public String getWaveImageUri() {
        return waveImageUri;
    }

    public void setWaveImageUri(String waveImageUri) {
        this.waveImageUri = waveImageUri;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }
}
