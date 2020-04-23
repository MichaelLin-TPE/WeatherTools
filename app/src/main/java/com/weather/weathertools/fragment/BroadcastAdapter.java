package com.weather.weathertools.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.WeatherLocation;

import java.util.ArrayList;

public class BroadcastAdapter extends RecyclerView.Adapter<BroadcastAdapter.ViewHolder> {
    private ArrayList<WeatherLocation> dataArray;

    private Context context;

    private OnBroadcastItemClickListener listener;

    private ArrayList<Boolean> isOpenArray;

    public void setOnBroadcastItemClickListener(OnBroadcastItemClickListener listener){
        this.listener = listener;
    }

    public BroadcastAdapter(ArrayList<WeatherLocation> dataArray, Context context) {
        this.dataArray = dataArray;
        this.context = context;
        isOpenArray = new ArrayList<>();
        for (WeatherLocation data : dataArray){
            isOpenArray.add(false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.broadcast_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        WeatherLocation data = dataArray.get(position);
        holder.tvTitle.setText(data.getLocationName());
        final BroacastItemAdapter adapter = new BroacastItemAdapter(data.getWeatherElement(),context);
        holder.recyclerView.setAdapter(adapter);

        if (isOpenArray.get(position)){
            holder.ivIcon.setImageResource(R.drawable.up);
            holder.recyclerView.setVisibility(View.VISIBLE);
        }else {
            holder.ivIcon.setImageResource(R.drawable.down);
            holder.recyclerView.setVisibility(View.GONE);
        }

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
                isOpenArray.set(position,!isOpenArray.get(position));
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;

        private TextView tvTitle;

        private ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.broadcast_item_recycler_view);
            tvTitle = itemView.findViewById(R.id.broadcast_item_title);
            ivIcon = itemView.findViewById(R.id.broadcast_item_icon);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(manager);
            if (recyclerView.getItemAnimator() != null){
                ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(true);
                recyclerView.getItemAnimator().setChangeDuration(500);
                recyclerView.getItemAnimator().setMoveDuration(500);
            }
        }
    }

    public interface OnBroadcastItemClickListener{
        void onClick();
    }
}
