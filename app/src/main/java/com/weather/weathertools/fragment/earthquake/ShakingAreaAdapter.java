package com.weather.weathertools.fragment.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeShakingArea;

import java.util.ArrayList;

public class ShakingAreaAdapter extends RecyclerView.Adapter<ShakingAreaAdapter.ViewHolder> {

    private Context context;

    private ArrayList<EarthquakeShakingArea> dataArray;

    public ShakingAreaAdapter(Context context, ArrayList<EarthquakeShakingArea> dataArray) {
        this.context = context;
        this.dataArray = dataArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.shaking_area_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EarthquakeShakingArea data = dataArray.get(position);
        holder.tvTitle.setText(data.getAreaName());

        LocationAdapter adapter = new LocationAdapter(dataArray.get(position).getEqStation(),context);
        holder.recyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;

        private TextView tvTitle;

        private ImageView ivIcon;

        private ConstraintLayout clickArea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.shaking_item_recycler_view);
            tvTitle = itemView.findViewById(R.id.shaking_item_title);
            ivIcon = itemView.findViewById(R.id.shaking_item_icon);
            clickArea = itemView.findViewById(R.id.shaking_item_click_area);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
