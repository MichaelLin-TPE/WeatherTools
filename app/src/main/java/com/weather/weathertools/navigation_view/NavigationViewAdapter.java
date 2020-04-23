package com.weather.weathertools.navigation_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.navigation_view.view.BroadCastAdapter;
import com.weather.weathertools.navigation_view.view.BroadcastViewHolder;

import java.util.ArrayList;

import static com.weather.weathertools.navigation_view.NavigationPresenterImpl.BROADCAST;
import static com.weather.weathertools.navigation_view.NavigationPresenterImpl.EARTHQUAKE;
import static com.weather.weathertools.navigation_view.NavigationPresenterImpl.PRE_LOOK;
import static com.weather.weathertools.navigation_view.NavigationPresenterImpl.WEATHER_STATUS;

public class NavigationViewAdapter extends RecyclerView.Adapter {


    private NavigationPresenter presenter;

    private Context context;

    private BroadCastAdapter.OnNavigationItemClickListener listener;

    public void setOnNavigationItemClickListener(BroadCastAdapter.OnNavigationItemClickListener listener){
        this.listener = listener;
    }

    public NavigationViewAdapter(NavigationPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case BROADCAST:
                return  new BroadcastViewHolder(LayoutInflater.from(context).inflate(R.layout.broadcast_view,parent,false),context);
            case PRE_LOOK:
                return  null;
            case EARTHQUAKE:
                return  null;
            case WEATHER_STATUS:
                return null;
                default:
                    return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BroadcastViewHolder){
            presenter.onBindBroadcastViewHolder((BroadcastViewHolder)holder,position);
            presenter.onNavigationItemClickListener((BroadcastViewHolder)holder,listener);
        }
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return presenter.getItemViewType(position);
    }
}
