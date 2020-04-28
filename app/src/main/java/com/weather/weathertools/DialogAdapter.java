package com.weather.weathertools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.ViewHolder> {

    private ArrayList<String> dataArray;

    private Context context;

    private OnDialogItemClickListener listener;

    private int userPressIndex;

    public void setOnDialogItemClickListener(OnDialogItemClickListener listener){
        this.listener =listener;
    }

    public DialogAdapter(ArrayList<String> dataArray, Context context) {
        this.dataArray = dataArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.dialog_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String title = dataArray.get(position);
        holder.tvTitle.setText(title);

        if (userPressIndex == position){
            holder.tvTitle.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_selector));
        }else {
            holder.tvTitle.setBackground(null);
        }

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPressIndex = position;
                listener.onClick(title);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.dialog_item_title);
        }
    }

    public interface OnDialogItemClickListener{
        void onClick(String title);
    }
}
