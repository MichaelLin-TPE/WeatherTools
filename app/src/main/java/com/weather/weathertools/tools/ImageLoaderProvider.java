package com.weather.weathertools.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.weather.weathertools.R;

public class ImageLoaderProvider {
    private static ImageLoaderProvider imageLoaderProvider;

    private ImageLoader imageLoader = ImageLoader.getInstance();

    private DisplayImageOptions options;

    public static synchronized ImageLoaderProvider getInstance(Context context){
        if (imageLoaderProvider == null){
            imageLoaderProvider = new ImageLoaderProvider(context);
        }
        return imageLoaderProvider;
    }

    private ImageLoaderProvider(Context context){
        initImageLoader(context);
    }

    private void initImageLoader(Context context) {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.app_icon)
                .showImageOnFail(R.drawable.app_icon)
                .showImageOnLoading(R.drawable.app_icon)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(options).build();
        imageLoader.init(config);
    }

    public void setPhotoUrl(String url, RoundedImageView ivImage){
        imageLoader.displayImage(url,ivImage,options);
    }

    public void setPhotoUrl(String url, ImageView ivImage){
        imageLoader.displayImage(url,ivImage,options);
    }
}
