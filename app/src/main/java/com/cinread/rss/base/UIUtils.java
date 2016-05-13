package com.cinread.rss.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

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
public class UIUtils {
    public static Context getContext() {
        return MyApplication.getContext();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static long getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    public static Handler getMainThreadHandler() {
        return MyApplication.getHandler();
    }

    public static DbUtils getDbManager() {
        return MyApplication.getDbManager();
    }

    public static File getAppFile() {
        return MyApplication.getAppFile();
    }

    public static void postTask(Runnable task) {
        long curThreadId = android.os.Process.myTid();
        long mainThreadId = getMainThreadId();
        if (curThreadId == mainThreadId) {
            task.run();
        } else {
            getMainThreadHandler().post(task);
        }
    }

    public static void postDelay(Runnable task, long delayed){
        getMainThreadHandler().postDelayed(task,delayed);
    }

    public static int dp2px(int dip) {
        //px/dp = density ①
        //px/(ppi/160) = dp ②
        float density = UIUtils.getResources().getDisplayMetrics().density;
        //ppi
        int densityDpi = UIUtils.getResources().getDisplayMetrics().densityDpi;
        int px = (int) (dip * density + .5f);
        return px;
    }

    public static int px2dp(int px) {
        //px/dp = density ①
        //px和dp倍数关系
        float density = UIUtils.getResources().getDisplayMetrics().density;
        int dp = (int) (px / density + .5f);
        return dp;
    }
}
