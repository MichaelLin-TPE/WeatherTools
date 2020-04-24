package com.weather.weathertools.fragment.broadcast_one_week;

import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysLocation;

import java.util.ArrayList;

public interface BroadcastOneWeekVu {
    void setRecyclerView(ArrayList<WeatherTwoDaysLocation> location);
}
