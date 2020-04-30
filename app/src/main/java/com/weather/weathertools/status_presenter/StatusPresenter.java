package com.weather.weathertools.status_presenter;

import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;
import com.weather.weathertools.main_view.ForecastViewHolder;
import com.weather.weathertools.main_view.MapViewHolder;

import java.util.ArrayList;

public interface StatusPresenter {
    int getItemCount();

    int getItemViewType(int position);

    void onBindForecastViewHolder(ForecastViewHolder holder, int position);

    void setData(ArrayList<WeatherTwoDaysElement> dataArray);

    void onBindMapViewHolder(MapViewHolder holder, int position);

    void setLocation(double latitude, double longitude);
}
