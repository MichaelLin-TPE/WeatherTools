package com.weather.weathertools.fragment.earthquake.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.earthquake.ShakingAreaAdapter;
import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeShakingArea;

import java.util.ArrayList;

public class EarthquakeLocationViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView recyclerView;

    private Context context;

    public EarthquakeLocationViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        recyclerView = itemView.findViewById(R.id.earthquake_location_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setData(ArrayList<EarthquakeShakingArea> shakingArea) {
        ShakingAreaAdapter areaAdapter = new ShakingAreaAdapter(context,shakingArea);
        recyclerView.setAdapter(areaAdapter);
    }
}
