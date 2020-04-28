package com.weather.weathertools;

import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysLocation;

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

    void showMainActivity();

    void replaceEarthquakeFragment(String apiUrl);

    void showLocationDialog(ArrayList<String> locationArray);

    void setDialogRecyclerView(ArrayList<String> locationArray);

    void setLocationTitle(String location);
}
