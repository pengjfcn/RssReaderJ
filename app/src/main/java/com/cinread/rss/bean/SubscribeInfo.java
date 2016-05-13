package com.cinread.rss.bean;

import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * @project：Rss
 * @package：com.cinread.rss
 * @author：pengjf
 * @update：2016/4/9
 * @desc： 订阅
 */
// Created by pengjf on 2016/4/9.
@Table(name = "tb_mysubscribe")
public class SubscribeInfo {

    private Integer id;
    private String cover;
    private String title;
    @Unique
    private String url;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
