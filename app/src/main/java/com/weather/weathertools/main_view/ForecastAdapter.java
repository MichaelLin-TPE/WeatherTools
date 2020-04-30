package com.weather.weathertools.main_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private ArrayList<WeatherTwoDaysElement> dataArray;

    private ArrayList<String> minTempArray,comfortArray,rainArray,weatherArray,timeArray,maxTempArray;

    private Context context;

    public ForecastAdapter(ArrayList<WeatherTwoDaysElement> dataArray, Context context) {
        this.context = context;
        this.dataArray = dataArray;
        minTempArray = new ArrayList<>();
        comfortArray = new ArrayList<>();
        rainArray = new ArrayList<>();
        weatherArray = new ArrayList<>();
        timeArray = new ArrayList<>();
        maxTempArray = new ArrayList<>();
        for (int i = 0 ; i < dataArray.size() ; i ++){
            for (int j = 0 ; j < dataArray.get(i).getTime().size() ; j++){
                if (dataArray.get(i).getElemtNmae().equals("Wx")){
                    weatherArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue());
                    timeArray.add(dataArray.get(i).getTime().get(j).getStartTime()+"~\n"+dataArray.get(i).getTime().get(j).getEndTime());
                }else if (dataArray.get(i).getElemtNmae().equals("PoP12h")){
                    if (j < 12){
                        rainArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue()+"%");
                        rainArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue()+"%");
                    }
                }else if (dataArray.get(i).getElemtNmae().equals("MinT")){
                    minTempArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue()+"°C");
                }else if (dataArray.get(i).getElemtNmae().equals("MaxT")){
                    maxTempArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue()+"°C");
                } else if (dataArray.get(i).getElemtNmae().equals("MaxCI")){
                    comfortArray.add(dataArray.get(i).getTime().get(j).getElementValue().get(0).getValue());
                }
            }
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.forecast_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvWeek.setText(getWeekDay(timeArray.get(position)));
        holder.tvTime.setText(getTime(timeArray.get(position)));
        holder.tvRain.setText(rainArray.get(position));
        holder.tvMax.setText(maxTempArray.get(position));
        holder.tvMin.setText(minTempArray.get(position));
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

    private String getTime(String time) {
        String timeStr = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN);
        try{
            Date date = format.parse(time);
            SimpleDateFormat weekFormat = new SimpleDateFormat("HH:mm",Locale.TAIWAN);
            if (date != null){
                timeStr = weekFormat.format(date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return timeStr;
    }

    private String getWeekDay(String time) {
        String timeStr = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN);
        try{
            Date date = format.parse(time);
            SimpleDateFormat weekFormat = new SimpleDateFormat("E",Locale.TAIWAN);
            if (date != null){
                timeStr = weekFormat.format(date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return timeStr;
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.get(0).getTime().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvWeek,tvTime,tvMax,tvMin,tvRain;

        private ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRain = itemView.findViewById(R.id.forecast_item_rain);
            tvWeek = itemView.findViewById(R.id.forecast_item_week_day);
            tvTime = itemView.findViewById(R.id.forecast_item_time);
            tvMax = itemView.findViewById(R.id.forecast_item_max);
            tvMin = itemView.findViewById(R.id.forecast_item_min);
            ivIcon = itemView.findViewById(R.id.forecast_item_icon);

        }
    }
}
