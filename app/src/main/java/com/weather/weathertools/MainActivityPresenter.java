package com.weather.weathertools;

import java.util.ArrayList;

public interface MainActivityPresenter {
    void onNavigationButtonClickListener();

    void onShowNavigationView(ArrayList<String> broadCastArray);

    void onNavigationItemClickListener(String name, ArrayList<String> apiUrlArray, ArrayList<String> oneWeekArray);

    void onStartToGetApiData(String address, ArrayList<String> oneWeekApiUrlArray);

    void onNavigationBackButtonClickListener();

    void onLocationButtonClickListener();

    void onSpinnerItemClickListener(String city, ArrayList<String> oneWeekApiUrlArray);

    void onDialogItemClickListener(String location);
}
