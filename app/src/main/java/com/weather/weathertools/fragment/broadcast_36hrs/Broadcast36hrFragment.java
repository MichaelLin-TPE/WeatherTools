package com.weather.weathertools.fragment.broadcast_36hrs;

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
import com.weather.weathertools.fragment.json_parser.thirty_hours.WeatherLocation;

import java.util.ArrayList;


public class Broadcast36hrFragment extends Fragment implements Broadcast36hrVu{


    private String apiUrl;

    private Context context;

    private Broadcast36hrPresenter presenter;

    private RecyclerView recyclerView;

    public static Broadcast36hrFragment newInstance(String url) {
        Broadcast36hrFragment fragment = new Broadcast36hrFragment();
        Bundle args = new Bundle();
        args.putString("api_url",url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        initBundle();
        presenter.onStartGetData(apiUrl);
    }

    private void initBundle() {
        if (getArguments() != null) {
            apiUrl = getArguments().getString("api_url");
        }
    }

    private void initPresenter() {
        presenter = new Broadcast36hrPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broadcast36hr, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.broadcast_36hr_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
    }


    @Override
    public void setRecyclerView(ArrayList<WeatherLocation> dataArray) {
        final BroadcastAdapter adapter = new BroadcastAdapter(dataArray,context);
        recyclerView.setAdapter(adapter);
        adapter.setOnBroadcastItemClickListener(new BroadcastAdapter.OnBroadcastItemClickListener() {
            @Override
            public void onClick() {
                //這裡只是拿來接點擊事件而已 不做任何事情
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
