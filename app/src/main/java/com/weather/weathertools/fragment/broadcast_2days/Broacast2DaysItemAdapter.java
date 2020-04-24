package com.weather.weathertools.fragment.broadcast_2days;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;
import com.weather.weathertools.fragment.json_parser.thirty_hours.WeatherElement;

import java.util.ArrayList;

public class Broacast2DaysItemAdapter extends RecyclerView.Adapter<Broacast2DaysItemAdapter.ViewHolder> {

    private ArrayList<WeatherTwoDaysElement> dataArray;

    private Context context;

    private ArrayList<String> minTempArray,comfortArray,rainArray,weatherArray,timeArray;

    public Broacast2DaysItemAdapter(ArrayList<WeatherTwoDaysElement> dataArray, Context context) {
        this.dataArray = dataArray;
        this.context = context;

        minTempArray = new ArrayList<>();
        comfortArray = new ArrayList<>();
        rainArray = new ArrayList<>();
        weatherArray = new ArrayList<>();
        timeArray = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        for (int i = 0 ; i < dataArray.size() ; i ++){
            for (int j = 0 ; j < dataArray.get(i).getTime().size() ; j++){
                if (dataArray.get(i).getElemtNmae().equals("Wx")){
                    weatherArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue());
                    timeArray.add(dataArray.get(i).getTime().get(j).getStartTime()+"~\n"+dataArray.get(i).getTime().get(j).getEndTime());
                }else if (dataArray.get(i).getElemtNmae().equals("PoP6h")){
                    if (j < 12){
                        rainArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue()+"%");
                        rainArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue()+"%");
                    }
                }else if (dataArray.get(i).getElemtNmae().equals("AT")){
                    minTempArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue()+"°C");
                }else if (dataArray.get(i).getElemtNmae().equals("CI")){
                    comfortArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(1).getValue());
                }
            }
        }

        holder.tvComfort.setText(comfortArray.get(position));
        holder.tvRain.setText(rainArray.get(position));
        holder.tvTemp.setText(minTempArray.get(position));
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
        Log.i("Michael","兩天預報的資料長度 : "+dataArray.size());
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
