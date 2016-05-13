package com.cinread.rss.base;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * @project：book
 * @package：com.cinread.book.base
 * @author：pengjf
 * @update：2016/3/9
 * @desc： TODO
 */
// Created by pengjf on 2016/3/9.
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public List<T> mDataSet;

    public MyBaseAdapter(List<T> data) {
        this.mDataSet = data;
    }

    @Override
    public int getCount() {
        int count;
        if (mDataSet != null) {
            if (mDataSet.size()>30)
                count = 30;
            else
                count = mDataSet.size();
            return count;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDataSet != null) {
            return mDataSet.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
