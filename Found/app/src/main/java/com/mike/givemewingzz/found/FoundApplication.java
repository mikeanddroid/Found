package com.mike.givemewingzz.found;

import android.app.Application;
import android.content.Context;

import com.mike.givemewingzz.found.utils.DBHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;

import io.realm.Realm;

/**
 * Created by GiveMeWingzz on 12/9/2015.
 */
public class FoundApplication extends Application {

    public static Context context;
    public static FoundApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        context = this.getApplicationContext();
        // Todo : Have to add after the retrofit integration.
        Realm.setDefaultConfiguration(DBHelper.getRealmConfig(this));
        initImageLoader(context);

    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.MAX_PRIORITY)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCacheSizePercentage(25).threadPoolSize(5)
                        //.writeDebugLogs() // Remove for release app
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        L.writeLogs(false);
    }

    public static FoundApplication getInstance() {
        return mainApplication;
    }

}
