package com.weather.weathertools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.weather.weathertools.fragment.Broadcast36hrFragment;
import com.weather.weathertools.navigation_view.NavigationPresenter;
import com.weather.weathertools.navigation_view.NavigationPresenterImpl;
import com.weather.weathertools.navigation_view.NavigationViewAdapter;
import com.weather.weathertools.navigation_view.view.BroadCastAdapter;
import com.weather.weathertools.tools.TitleProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityVu{

    private DrawerLayout drawerLayout;

    private RecyclerView navigationRecyclerView;

    private MainActivityPresenter presenter;

    private NavigationPresenter navigationPresenter;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initPresenter();
        initView();

        presenter.onShowNavigationView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navigationRecyclerView = findViewById(R.id.navigation_recycler_view);
        navigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onNavigationButtonClickListener();
            }
        });
    }

    private void initPresenter() {
        presenter = new MainActivityPresenterImpl(this);
        navigationPresenter = new NavigationPresenterImpl();
    }

    @Override
    public void openDrawerLayout(boolean isOpen) {
        if (isOpen){
            drawerLayout.openDrawer(GravityCompat.START);
        }else {
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void showNavigationView() {
       navigationPresenter.setBroadCastData(TitleProvider.getInstance(this).getBroadCastArray());
       NavigationViewAdapter adapter = new NavigationViewAdapter(navigationPresenter,this);
       navigationRecyclerView.setAdapter(adapter);
       adapter.setOnNavigationItemClickListener(new BroadCastAdapter.OnNavigationItemClickListener() {
           @Override
           public void onClick(String name) {
               Log.i("Michael","點擊了 : "+name);
               presenter.onNavigationItemClickListener(name);
           }
       });
    }

    @Override
    public String get36hrString() {
        return getString(R.string.broad_title_1);
    }

    @Override
    public void replace36hrFragment(String apiUrl) {
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout,Broadcast36hrFragment.newInstance(apiUrl)).commit();
    }


}
