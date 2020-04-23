package com.weather.weathertools;

public interface MainActivityVu {
    void openDrawerLayout(boolean isOpen);

    void showNavigationView();

    String get36hrString();

    void replace36hrFragment(String apiUrl);
}
