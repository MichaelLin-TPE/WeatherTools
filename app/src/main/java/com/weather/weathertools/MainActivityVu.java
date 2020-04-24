package com.weather.weathertools;

import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;

import java.util.ArrayList;

public interface MainActivityVu {
    void openDrawerLayout(boolean isOpen);

    void showNavigationView();

    String get36hrString();

    void replace36hrFragment(String apiUrl);

    void setTitle(String name);

    void replace2DaysFragment(String apiUrl);

    void replaceOneWeekFragment(String s);

    void setRecyclerView(ArrayList<WeatherTwoDaysElement> dataArray);
}
