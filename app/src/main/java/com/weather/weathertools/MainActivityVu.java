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

    void setRecyclerView(ArrayList<WeatherTwoDaysElement> dataArray, double latitude, double longitude);

    void showMainActivity();

    void replaceEarthquakeFragment(String apiUrl);

    void showLocationDialog(ArrayList<String> locationArray);

    void setDialogRecyclerView(String city, ArrayList<String> locationArray);

    void setLocationTitle(String location);

    void showErrorCodeDialog(String errorCode);

    void setBackground(String weatherStatus);

    void hideProgress(boolean isShow);
}
