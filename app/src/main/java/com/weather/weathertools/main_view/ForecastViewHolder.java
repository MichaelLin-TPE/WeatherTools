package com.weather.weathertools.main_view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;

import java.util.ArrayList;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView recyclerView;

    private Context context;

    public ForecastViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        recyclerView = itemView.findViewById(R.id.forecast_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setData(ArrayList<WeatherTwoDaysElement> dataArray) {

        ForecastAdapter adapter = new ForecastAdapter(dataArray,context);
        recyclerView.setAdapter(adapter);
    }
}
