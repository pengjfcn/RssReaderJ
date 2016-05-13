package com.cinread.rss.bean;

import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * @project：Rss
 * @package：com.cinread.rss
 * @author：pengjf
 * @update：2016/4/9
 * @desc： 收藏、Rss
 */
// Created by pengjf on 2016/4/9.
@Table(name = "tb_mycollect")
public class CollectInfo {
    private Integer id;

    private String cover;     //源logo
    private String rssname;  //订阅源
    @Unique
    private String title;  //大标题
    private String desc;   //标题描述
    private String time;   //收藏时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getRssname() {
        return rssname;
    }

    public void setRssname(String rssname) {
        this.rssname = rssname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
