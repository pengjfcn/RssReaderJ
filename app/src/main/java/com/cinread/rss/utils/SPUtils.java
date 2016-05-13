package com.cinread.rss.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @project：book
 * @package：com.cinread.book.utils
 * @author：pengjf
 * @update：2016/3/11
 * @desc： TODO
 */
// Created by pengjf on 2016/3/11.
public class SPUtils {
    private static final String NAME = "rss";
    private static SharedPreferences mPreferences;

    /**
     * 获取mPreferences
     *
     * @param context
     * @return
     */
    public static SharedPreferences getPreferences(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    /**
     * 获取boolean类型的数据，如果没有返回false
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 获取boolean类型的数据,此方法频繁调用
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        mPreferences = getPreferences(context);
        return mPreferences.getBoolean(key, defValue);
    }

    /**
     * 存储boolean类型的数据
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getPreferences(context);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 获得String类型的数据,如果没有返回null
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 获得String类型的数据 频繁的读文件
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = getPreferences(context);
        return sp.getString(key, defValue);
    }

    /**
     * 存储String类型的数据
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = getPreferences(context);
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获得int类型的数据,如果没有返回-1
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * 获得int类型的数据 频繁的读文件
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = getPreferences(context);
        return sp.getInt(key, defValue);
    }

    /**
     * 存储int类型的数据
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = getPreferences(context);
        sp.edit().putInt(key, value).commit();
    }
}
