package com.weather.weathertools.fragment.earthquake.view_presenter;

import com.weather.weathertools.fragment.earthquake.view.EarthquakeImageViewHolder;
import com.weather.weathertools.fragment.earthquake.view.EarthquakeLocationViewHolder;
import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeObject;

public interface ViewPresenter {
    int getItemCount();

    int getItemViewType(int position);

    void setData(EarthquakeObject data);

    void onBindImageViewHolder(EarthquakeImageViewHolder holder, int position);

    void onBindLocationViewHolder(EarthquakeLocationViewHolder holder, int position);
}
