package com.weather.weathertools.fragment.broadcast_36hrs;

import com.weather.weathertools.fragment.json_parser.thirty_hours.WeatherLocation;

import java.util.ArrayList;

public interface Broadcast36hrVu {
    void setRecyclerView(ArrayList<WeatherLocation> dataArray);

    void showErrorCodeDialog(String errorCode);
}
