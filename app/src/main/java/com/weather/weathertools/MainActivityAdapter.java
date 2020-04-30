package com.weather.weathertools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.main_view.ForecastViewHolder;
import com.weather.weathertools.main_view.MapViewHolder;
import com.weather.weathertools.status_presenter.StatusPresenter;

import static com.weather.weathertools.status_presenter.StatusPresenterImpl.FORECAST;
import static com.weather.weathertools.status_presenter.StatusPresenterImpl.MAP;

public class MainActivityAdapter extends RecyclerView.Adapter {

    private StatusPresenter presenter;

    private Context context;

    public MainActivityAdapter(StatusPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case FORECAST:
                return new ForecastViewHolder(LayoutInflater.from(context).inflate(R.layout.forecast_view,parent,false),context);
            case MAP:
                return new MapViewHolder(LayoutInflater.from(context).inflate(R.layout.map_view,parent,false),context);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ForecastViewHolder){
            presenter.onBindForecastViewHolder((ForecastViewHolder)holder,position);
        }
        if (holder instanceof MapViewHolder){
            presenter.onBindMapViewHolder((MapViewHolder)holder,position);
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
