package com.weather.weathertools.fragment.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.earthquake.view.EarthquakeImageViewHolder;
import com.weather.weathertools.fragment.earthquake.view.EarthquakeLocationViewHolder;
import com.weather.weathertools.fragment.earthquake.view_presenter.ViewPresenter;

import static com.weather.weathertools.fragment.earthquake.view_presenter.ViewPresenterImpl.IMAGE;
import static com.weather.weathertools.fragment.earthquake.view_presenter.ViewPresenterImpl.LOCATION;

public class EarthquakeAdapter extends RecyclerView.Adapter {

    private Context context;

    private ViewPresenter presenter;

    public EarthquakeAdapter(Context context, ViewPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case IMAGE:
                return new EarthquakeImageViewHolder(LayoutInflater.from(context).inflate(R.layout.earthquake_image_view, parent, false),context);
            case LOCATION:
                return new EarthquakeLocationViewHolder(LayoutInflater.from(context).inflate(R.layout.earthquake_location_view, parent, false), context);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EarthquakeImageViewHolder){
            presenter.onBindImageViewHolder((EarthquakeImageViewHolder)holder,position);
        }
        if (holder instanceof EarthquakeLocationViewHolder){
            presenter.onBindLocationViewHolder((EarthquakeLocationViewHolder)holder,position);
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
