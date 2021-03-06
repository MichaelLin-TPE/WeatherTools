package com.weather.weathertools.fragment.broadcast_2days;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.BroadcastAdapter;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysLocation;

import java.util.ArrayList;


public class Broadcast2DaysFragment extends Fragment implements Broadcast2DaysVu{

    private String apiUrl;

    private Context context;

    private RecyclerView recyclerView;

    private Broadcast2DaysPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static Broadcast2DaysFragment newInstance(String apiUrl) {
        Broadcast2DaysFragment fragment = new Broadcast2DaysFragment();
        Bundle args = new Bundle();
        args.putString("api_url", apiUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        initBundle();
        presenter.onStartGetData(apiUrl);
    }

    private void initPresenter() {
        presenter = new Broadcast2DaysPresenterImpl(this);
    }

    private void initBundle() {
        if (getArguments() != null) {
            apiUrl = getArguments().getString("api_url","");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broadcast2_days, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.broadcast_2days_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
    }


    @Override
    public void setRecyclerView(ArrayList<WeatherTwoDaysLocation> location) {
        Broadcast2DaysAdapter adapter = new Broadcast2DaysAdapter(location,context);
        recyclerView.setAdapter(adapter);
        adapter.setOnBroadcastItemClickListener(new BroadcastAdapter.OnBroadcastItemClickListener() {
            @Override
            public void onClick() {

            }
        });
    }

    @Override
    public void showErrorCodeDialog(String errorCode) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(getString(R.string.information_fail))
                .setMessage(getString(R.string.get_error_code)+errorCode)
                .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }
}
