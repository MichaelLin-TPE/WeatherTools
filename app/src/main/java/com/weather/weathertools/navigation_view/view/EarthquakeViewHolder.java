package com.weather.weathertools.navigation_view.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.BroadcastAdapter;

import java.util.ArrayList;

public class EarthquakeViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView recyclerView;

    private Context context;

    private BroadCastAdapter.OnNavigationItemClickListener listener;

    private TextView tvTitle;

    private ImageView ivIcon;

    private int height;

    private boolean isShow;

    public void setOnNavigationItemClickListener(BroadCastAdapter.OnNavigationItemClickListener listener){
        this.listener = listener;
    }


    public EarthquakeViewHolder(@NonNull View itemView,Context context) {
        super(itemView);

        this.context = context;
        recyclerView = itemView.findViewById(R.id.earthquake_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        tvTitle = itemView.findViewById(R.id.earthquake_title);
        ivIcon = itemView.findViewById(R.id.earthquake_icon);
    }

    public void setData(ArrayList<String> earthquakeTitleArray) {
        final BroadCastAdapter adapter = new BroadCastAdapter(earthquakeTitleArray,context);
        recyclerView.setAdapter(adapter);
        adapter.setOnNavigationItemClickListener(new BroadCastAdapter.OnNavigationItemClickListener() {
            @Override
            public void onClick(String name) {
                adapter.notifyDataSetChanged();
                listener.onClick(name);
            }
        });
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = recyclerView.getHeight();
                recyclerView.setPadding(0,-height,0,0);
            }
        });

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeView(isShow);
                isShow = !isShow;
            }
        });
    }

    private void changeView(boolean isShow) {
        ValueAnimator valueAnimator = new ValueAnimator();
        if (!isShow){
            valueAnimator.setIntValues(-height,0);
        }else {
            valueAnimator.setIntValues(0,-height);
        }

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int)animation.getAnimatedValue();
                recyclerView.setPadding(0,value,0,0);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tvTitle.setClickable(true);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                tvTitle.setClickable(false);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
        ViewCompat.animate(ivIcon).rotationBy(180f).setDuration(500).start();
    }
}
