package com.weather.weathertools;

import java.util.ArrayList;

public interface MainActivityPresenter {
    void onNavigationButtonClickListener();

    void onShowNavigationView(ArrayList<String> broadCastArray);

    void onNavigationItemClickListener(String name, ArrayList<String> apiUrlArray, ArrayList<String> oneWeekArray);

    void onStartToGetApiData(String address, ArrayList<String> oneWeekApiUrlArray);
}
