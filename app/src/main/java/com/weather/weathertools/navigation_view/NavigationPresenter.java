package com.weather.weathertools.navigation_view;

import com.weather.weathertools.navigation_view.view.BroadCastAdapter;
import com.weather.weathertools.navigation_view.view.BroadcastViewHolder;

import java.util.ArrayList;

public interface NavigationPresenter {
    int getItemCount();

    int getItemViewType(int position);

    void onBindBroadcastViewHolder(BroadcastViewHolder holder, int position);

    void setBroadCastData(ArrayList<String> broadCastArray);

    void onNavigationItemClickListener(BroadcastViewHolder holder, BroadCastAdapter.OnNavigationItemClickListener listener);
}
