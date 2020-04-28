package com.weather.weathertools.navigation_view;

import com.weather.weathertools.navigation_view.view.BroadCastAdapter;
import com.weather.weathertools.navigation_view.view.BroadcastViewHolder;
import com.weather.weathertools.navigation_view.view.EarthquakeViewHolder;

import java.util.ArrayList;

public class NavigationPresenterImpl implements NavigationPresenter{
    //預報
    public static final int BROADCAST = 0;
    //觀測
    public static final int PRE_LOOK = 1;
    //地震海嘯
    public static final int EARTHQUAKE = 2;
    //氣候
    public static final int WEATHER_STATUS = 3;

    private ArrayList<String> broadcatArray = new ArrayList<>();

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return BROADCAST;
        }
        if (position == 1){
            return EARTHQUAKE;
        }
        return 0;
    }

    @Override
    public void onBindBroadcastViewHolder(BroadcastViewHolder holder, int position) {

        holder.setData(broadcatArray);
    }

    @Override
    public void setBroadCastData(ArrayList<String> broadCastArray) {
        this.broadcatArray =broadCastArray;
    }

    @Override
    public void onNavigationItemClickListener(BroadcastViewHolder holder, BroadCastAdapter.OnNavigationItemClickListener listener) {
        holder.setOnNavigationItemClickListener(listener);
    }

    @Override
    public void onBindEarthquakeViewHolder(EarthquakeViewHolder holder, int position) {

        ArrayList<String> earthquakeTitleArray = new ArrayList<>();
        earthquakeTitleArray.add("顯著有感地震報告資料-顯著有感地震報告");

        holder.setData(earthquakeTitleArray);
    }

    @Override
    public void onNavigationEarthquakeItemClickListener(EarthquakeViewHolder holder, BroadCastAdapter.OnNavigationItemClickListener listener) {
        holder.setOnNavigationItemClickListener(listener);
    }
}
