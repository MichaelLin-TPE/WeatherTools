package com.weather.weathertools.fragment.json_parser.earthquake;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Earthquake implements Serializable {

    @SerializedName("earthquakeNo")
    private String earthquakeNo;
    @SerializedName("reportType")
    private String reportType;
    @SerializedName("reportColor")
    private String reportColor;
    @SerializedName("reportContent")
    private String reportContent;
    @SerializedName("reportImageURI")
    private String reportImageUri;
    @SerializedName("reportRemark")
    private String reportRemark;
    @SerializedName("web")
    private String web;
    @SerializedName("shakemapImageURI")
    private String shakeMapImageUri;
    @SerializedName("earthquakeInfo")
    private EarthquakeInfo earthquakeInfo;
    @SerializedName("intensity")
    private EarthquakeIntensity intensity;

    public String getEarthquakeNo() {
        return earthquakeNo;
    }

    public void setEarthquakeNo(String earthquakeNo) {
        this.earthquakeNo = earthquakeNo;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportColor() {
        return reportColor;
    }

    public void setReportColor(String reportColor) {
        this.reportColor = reportColor;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReportImageUri() {
        return reportImageUri;
    }

    public void setReportImageUri(String reportImageUri) {
        this.reportImageUri = reportImageUri;
    }

    public String getReportRemark() {
        return reportRemark;
    }

    public void setReportRemark(String reportRemark) {
        this.reportRemark = reportRemark;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getShakeMapImageUri() {
        return shakeMapImageUri;
    }

    public void setShakeMapImageUri(String shakeMapImageUri) {
        this.shakeMapImageUri = shakeMapImageUri;
    }

    public EarthquakeInfo getEarthquakeInfo() {
        return earthquakeInfo;
    }

    public void setEarthquakeInfo(EarthquakeInfo earthquakeInfo) {
        this.earthquakeInfo = earthquakeInfo;
    }

    public EarthquakeIntensity getIntensity() {
        return intensity;
    }

    public void setIntensity(EarthquakeIntensity intensity) {
        this.intensity = intensity;
    }
}
