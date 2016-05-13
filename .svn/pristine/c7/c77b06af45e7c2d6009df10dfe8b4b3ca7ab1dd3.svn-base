package com.cinread.rss.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinread.rss.R;
import com.cinread.rss.base.MyBaseAdapter;
import com.cinread.rss.base.UIUtils;
import com.cinread.rss.bean.ResultBean;
import com.cinread.rss.utils.LogUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * @project：Rss
 * @package：com.cinread.rss.adapter
 * @author：pengjf
 * @update：2016/4/15
 * @desc： TODO
 */
// Created by pengjf on 2016/4/15.
public class SearchResultAdapter extends MyBaseAdapter<ResultBean.ResultsBean> {

    private List<ResultBean.ResultsBean> mDatas;

    public SearchResultAdapter(List<ResultBean.ResultsBean> data) {
        super(data);
        this.mDatas = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(UIUtils.getContext(), R.layout.item_search_result, null);
            holder.ivCover = (ImageView) convertView.findViewById(R.id.isr_iv_logo);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.isr_tv_title);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.isr_tv_url);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResultBean.ResultsBean info = mDatas.get(position);
        BitmapUtils utils = new BitmapUtils(UIUtils.getContext());
        utils.display(holder.ivCover, info.getIconUrl());
        holder.tvTitle.setText(info.getTitle());
        String feedId = info.getFeedId();
        String[] strs = feedId.split("/");
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < strs.length; i++) {
            sb.append(strs[i]).append("/");
        }
        LogUtils.d(sb.toString());
        holder.tvDesc.setText(sb.toString());
        return convertView;
    }

    private class ViewHolder {
        ImageView ivCover;
        TextView  tvTitle;
        TextView  tvDesc;
    }
}
