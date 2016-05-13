package com.cinread.rss.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinread.rss.R;
import com.cinread.rss.base.MyBaseAdapter;
import com.cinread.rss.base.UIUtils;
import com.cinread.rss.bean.SubscribeInfo;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

/**
 * @project：Rss
 * @package：com.cinread.rss
 * @author：pengjf
 * @update：2016/4/9
 * @desc： TODO
 */
// Created by pengjf on 2016/4/9.
public class SubscribeAdapter extends MyBaseAdapter<SubscribeInfo> {

    private List<SubscribeInfo> mDatas;
    private DbUtils db = UIUtils.getDbManager();

    public SubscribeAdapter(List<SubscribeInfo> data) {
        super(data);
        this.mDatas = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(UIUtils.getContext(), R.layout.item_subscribe, null);
            holder.ivCover = (ImageView) convertView.findViewById(R.id.subscribe_iv_logo);
            holder.ivDel = (ImageView) convertView.findViewById(R.id.subscribe_iv_del);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.subscribe_tv_title);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.subscribe_tv_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final SubscribeInfo info = mDatas.get(position);
        BitmapUtils utils = new BitmapUtils(UIUtils.getContext());
        utils.display(holder.ivCover, info.getCover());
        String title = info.getTitle();
        holder.tvTitle.setText(title);
        holder.tvDesc.setText(info.getUrl());
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.delete(info);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView ivCover;
        ImageView ivDel;
        TextView  tvTitle;
        TextView  tvDesc;
    }
}