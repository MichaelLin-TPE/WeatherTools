package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EarthquakeMagnitude implements Serializable {
    @SerializedName("magnitdueType")
    private String magnitudeType;
    @SerializedName("magnitudeValue")
    private String magnitudeValue;

    public String getMagnitudeType() {
        return magnitudeType;
    }

    public void setMagnitudeType(String magnitudeType) {
        this.magnitudeType = magnitudeType;
    }

    public String getMagnitudeValue() {
        return magnitudeValue;
    }

    public void setMagnitudeValue(String magnitudeValue) {
        this.magnitudeValue = magnitudeValue;
    }
}
