package com.weather.weathertools.status_presenter;

import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;
import com.weather.weathertools.main_view.ForecastViewHolder;
import com.weather.weathertools.main_view.MapViewHolder;

import java.util.ArrayList;

public class StatusPresenterImpl implements StatusPresenter {


    public static final int FORECAST = 0;

    public static final int MAP = 1;

    private double latitude,longitude;


    private ArrayList<WeatherTwoDaysElement> dataArray;


    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0){
            return FORECAST;
        }
        if (position == 1){
            return MAP;
        }

        return 0;
    }

    @Override
    public void onBindForecastViewHolder(ForecastViewHolder holder, int position) {
        holder.setData(dataArray);
    }

    @Override
    public void setData(ArrayList<WeatherTwoDaysElement> dataArray) {
        this.dataArray = dataArray;
    }

    @Override
    public void onBindMapViewHolder(MapViewHolder holder, int position) {
        holder.setData(latitude,longitude);
    }

    @Override
    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
