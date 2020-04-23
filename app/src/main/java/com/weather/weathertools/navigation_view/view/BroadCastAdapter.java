package com.weather.weathertools.navigation_view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.tools.TitleProvider;

import java.util.ArrayList;

public class BroadCastAdapter extends RecyclerView.Adapter<BroadCastAdapter.ViewHolder> {

    private ArrayList<String> dataArray;

    private Context context;

    private OnNavigationItemClickListener listener;

    private int userPressIndex;

    public void setOnNavigationItemClickListener(OnNavigationItemClickListener listener){
        this.listener = listener;
    }

    public BroadCastAdapter(ArrayList<String> dataArray, Context context) {
        this.dataArray = dataArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.navigation_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final String title = dataArray.get(position);

        holder.tvTitle.setText(title);
        holder.clickArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPressIndex = position;
                listener.onClick(title);
            }
        });


        if (userPressIndex == position){
            holder.clickArea.setBackground(ContextCompat.getDrawable(context,R.color.blue_lite));
        }else {
            holder.clickArea.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ConstraintLayout clickArea;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.navigation_item_title);
            clickArea = itemView.findViewById(R.id.navigation_item_click_area);
        }
    }

    public interface OnNavigationItemClickListener{
        void onClick(String name);
    }
}
