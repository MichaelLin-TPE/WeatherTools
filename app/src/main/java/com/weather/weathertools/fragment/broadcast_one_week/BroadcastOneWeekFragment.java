package com.weather.weathertools.fragment.broadcast_one_week;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
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


public class BroadcastOneWeekFragment extends Fragment implements BroadcastOneWeekVu{


    private String apiUrl;

    private Context context;

    private RecyclerView recyclerView;

    private BroadcastOneWeekPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static BroadcastOneWeekFragment newInstance(String apiUrl) {
        BroadcastOneWeekFragment fragment = new BroadcastOneWeekFragment();
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
        presenter.onGetApiData(apiUrl);
    }

    private void initBundle() {
        if (getArguments() != null) {
            apiUrl = getArguments().getString("api_url");
        }
    }

    private void initPresenter() {
        presenter = new BroadcastOneWeekPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broadcast_one_week, container, false);
        initView(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.one_week_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
    }


    @Override
    public void setRecyclerView(ArrayList<WeatherTwoDaysLocation> location) {
        OneWeekAdapter adapter = new OneWeekAdapter(location,context);
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
