package com.weather.weathertools.tools;

import android.content.Context;
import android.widget.ProgressBar;

import com.weather.weathertools.R;

import java.util.ArrayList;

public class TitleProvider {
    private static TitleProvider titleProvider;

    private Context context;

    private TitleProvider (Context context){
        this.context = context;
    }
    public static TitleProvider getInstance(Context context){
        if (titleProvider == null) {
            titleProvider = new TitleProvider(context);
        }
        return titleProvider;
    }
    public ArrayList<String> getBroadCastArray(){
        ArrayList<String> braodCastArray = new ArrayList<>();
        braodCastArray.add(context.getString(R.string.broad_title_1));
        braodCastArray.add(context.getString(R.string.broad_title_2));
        braodCastArray.add(context.getString(R.string.broad_title_3));
        braodCastArray.add(context.getString(R.string.broad_title_4));
        braodCastArray.add(context.getString(R.string.broad_title_5));
        braodCastArray.add(context.getString(R.string.broad_title_6));
        braodCastArray.add(context.getString(R.string.broad_title_7));
        braodCastArray.add(context.getString(R.string.broad_title_8));
        braodCastArray.add(context.getString(R.string.broad_title_9));
        braodCastArray.add(context.getString(R.string.broad_title_10));
        braodCastArray.add(context.getString(R.string.broad_title_11));
        braodCastArray.add(context.getString(R.string.broad_title_12));
        braodCastArray.add(context.getString(R.string.broad_title_13));
        braodCastArray.add(context.getString(R.string.broad_title_14));
        braodCastArray.add(context.getString(R.string.broad_title_15));
        braodCastArray.add(context.getString(R.string.broad_title_16));
        braodCastArray.add(context.getString(R.string.broad_title_17));
        braodCastArray.add(context.getString(R.string.broad_title_18));
        braodCastArray.add(context.getString(R.string.broad_title_19));
        braodCastArray.add(context.getString(R.string.broad_title_20));
        braodCastArray.add(context.getString(R.string.broad_title_21));
        braodCastArray.add(context.getString(R.string.broad_title_22));
        braodCastArray.add(context.getString(R.string.broad_title_23));
        braodCastArray.add(context.getString(R.string.broad_title_24));
        braodCastArray.add(context.getString(R.string.broad_title_25));
        braodCastArray.add(context.getString(R.string.broad_title_26));
        braodCastArray.add(context.getString(R.string.broad_title_27));
        braodCastArray.add(context.getString(R.string.broad_title_28));
        braodCastArray.add(context.getString(R.string.broad_title_29));
        braodCastArray.add(context.getString(R.string.broad_title_30));
        braodCastArray.add(context.getString(R.string.broad_title_31));
        braodCastArray.add(context.getString(R.string.broad_title_32));
        braodCastArray.add(context.getString(R.string.broad_title_33));
        braodCastArray.add(context.getString(R.string.broad_title_34));
        braodCastArray.add(context.getString(R.string.broad_title_35));
        braodCastArray.add(context.getString(R.string.broad_title_36));
        braodCastArray.add(context.getString(R.string.broad_title_37));
        braodCastArray.add(context.getString(R.string.broad_title_38));
        braodCastArray.add(context.getString(R.string.broad_title_39));
        braodCastArray.add(context.getString(R.string.broad_title_40));
        braodCastArray.add(context.getString(R.string.broad_title_41));
        braodCastArray.add(context.getString(R.string.broad_title_42));
        braodCastArray.add(context.getString(R.string.broad_title_43));
        braodCastArray.add(context.getString(R.string.broad_title_44));
        braodCastArray.add(context.getString(R.string.broad_title_45));
        braodCastArray.add(context.getString(R.string.broad_title_46));
        braodCastArray.add(context.getString(R.string.broad_title_47));
        braodCastArray.add(context.getString(R.string.broad_title_48));
        braodCastArray.add(context.getString(R.string.broad_title_49));
        return braodCastArray;
    }
}
