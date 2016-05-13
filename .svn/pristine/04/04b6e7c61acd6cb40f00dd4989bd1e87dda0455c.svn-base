package com.cinread.rss.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.cinread.rss.R;
import com.cinread.rss.factory.DBFactory;
import com.cinread.rss.utils.Constants;
import com.lidroid.xutils.DbUtils;

import java.io.File;

/**
 * @project：book
 * @package：com.cinread.book.base
 * @author：pengjf
 * @update：2016/3/9
 * @desc： TODO
 */
// Created by pengjf on 2016/3/9.
public class MyApplication extends Application {
    private static Context mContext;
    private static Handler mHandler;
    private static long    mMainThreadId;
    private static File    mFile;
    private static DbUtils mDb;

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static DbUtils getDbManager() {
        return mDb;
    }

    public static File getAppFile() {
        return mFile;
    }

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        mFile = new File(Constants.DBPATH, mContext.getString(R.string.app_name));
        if (!mFile.exists())
            mFile.mkdirs();
        mDb = DBFactory.getDb();
        super.onCreate();
    }
}
