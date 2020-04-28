package com.weather.weathertools.fragment.earthquake.view_presenter;

import com.weather.weathertools.fragment.earthquake.view.EarthquakeImageViewHolder;
import com.weather.weathertools.fragment.earthquake.view.EarthquakeLocationViewHolder;
import com.weather.weathertools.fragment.json_parser.earthquake.Earthquake;
import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeObject;

public class ViewPresenterImpl implements ViewPresenter {

    private EarthquakeObject data;

    public static final int IMAGE = 0;

    public static final int LOCATION = 1;

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return IMAGE;
        }

        if (position == 1) {
            return LOCATION;
        }
        return 0;
    }

    @Override
    public void setData(EarthquakeObject data) {
        this.data = data;
    }

    @Override
    public void onBindImageViewHolder(EarthquakeImageViewHolder holder, int position) {
        holder.setData(data.getRecords().getEarthquake().get(0).getReportImageUri());
    }

    @Override
    public void onBindLocationViewHolder(EarthquakeLocationViewHolder holder, int position) {
        holder.setData(data.getRecords().getEarthquake().get(0).getIntensity().getShakingArea());
    }
}
