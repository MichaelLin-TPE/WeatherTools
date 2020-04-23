package com.weather.weathertools.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.WeatherElement;
import com.weather.weathertools.fragment.json_parser.WeatherTime;

import java.util.ArrayList;

public class BroacastItemAdapter extends RecyclerView.Adapter<BroacastItemAdapter.ViewHolder> {

    private ArrayList<WeatherElement> dataArray;

    private Context context;

    private ArrayList<String> minTempArray,maxTempArray,comfortArray,rainArray,weatherArray,timeArray,totalTempArray;

    public BroacastItemAdapter(ArrayList<WeatherElement> dataArray, Context context) {
        this.dataArray = dataArray;
        this.context = context;

        minTempArray = new ArrayList<>();
        maxTempArray = new ArrayList<>();
        comfortArray = new ArrayList<>();
        rainArray = new ArrayList<>();
        weatherArray = new ArrayList<>();
        timeArray = new ArrayList<>();
        totalTempArray = new ArrayList<>();
        for (int i = 0 ; i < dataArray.size() ; i ++){
            for (int j = 0 ; j < dataArray.get(i).getTime().size() ; j++){
                if (dataArray.get(i).getElementName().equals("Wx")){
                    weatherArray.add(dataArray.get(i).getTime().get(j).getParameter().getParameterName());
                    timeArray.add(dataArray.get(i).getTime().get(j).getStartTime()+"~\n"+dataArray.get(i).getTime().get(j).getEndTime());
                }else if (dataArray.get(i).getElementName().equals("PoP")){
                    rainArray.add(dataArray.get(i).getTime().get(j).getParameter().getParameterName()+"%");
                }else if (dataArray.get(i).getElementName().equals("MinT")){
                    minTempArray.add(dataArray.get(i).getTime().get(j).getParameter().getParameterName()+"~");
                }else if (dataArray.get(i).getElementName().equals("CI")){
                    comfortArray.add(dataArray.get(i).getTime().get(j).getParameter().getParameterName());
                }else if (dataArray.get(i).getElementName().equals("MaxT")){
                    maxTempArray.add(dataArray.get(i).getTime().get(j).getParameter().getParameterName()+"°C");
                }
            }
        }
        for (int i = 0 ; i < maxTempArray.size() ; i ++){
            totalTempArray.add(minTempArray.get(i)+maxTempArray.get(i));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvComfort.setText(comfortArray.get(position));
        holder.tvRain.setText(rainArray.get(position));
        holder.tvTemp.setText(totalTempArray.get(position));
        holder.tvTime.setText(timeArray.get(position));
        if (weatherArray.get(position).contains("雨")){
            holder.ivIcon.setImageResource(R.drawable.rain);
        }else if (weatherArray.get(position).contains("陰")){
            holder.ivIcon.setImageResource(R.drawable.cloudy);
        }else if (weatherArray.get(position).contains("晴")){
            holder.ivIcon.setImageResource(R.drawable.sun);
        }else {
            holder.ivIcon.setImageResource(R.drawable.cloudy);
        }
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.get(0).getTime().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;

        private TextView tvTemp,tvRain,tvComfort,tvTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.weather_big_icon);
            tvTemp = itemView.findViewById(R.id.weather_text_temp);
            tvRain = itemView.findViewById(R.id.weather_text_rain);
            tvComfort = itemView.findViewById(R.id.weather_text_comfort);
            tvTime = itemView.findViewById(R.id.weather_text_time);
        }
    }
}
