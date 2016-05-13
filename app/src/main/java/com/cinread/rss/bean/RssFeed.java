package com.cinread.rss.bean;

import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @project：Rss
 * @package：com.cinread.rss.rss
 * @author：pengjf
 * @update：2016/4/13
 * @desc： TODO
 */
// Created by pengjf on 2016/4/13.
@Table(name = "tb_myfeed")
public class RssFeed {

    private Integer id;
    private String  cover;
    private String  rssname;  //源
    @Unique
    private String  title; // 标题
    @Unique
    private String  pubdate; // 发布日期
    private String  link;

    private int           itemCount; // 用于计算列表的数目
    private List<RssItem> rssItems; // 用于描述列表item

    public RssFeed() {
        rssItems = new ArrayList<RssItem>();
    }

    // 添加RssItem条目,返回列表长度
    public int addItem(RssItem rssItem) {
        rssItems.add(rssItem);
        itemCount++;
        return itemCount;
    }

    // 根据下标获取RssItem
    public RssItem getItem(int position) {
        return rssItems.get(position);
    }

    public List<HashMap<String, Object>> getAllItems() {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < rssItems.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put(RssItem.TITLE, rssItems.get(i).getTitle());
            item.put(RssItem.PUBDATE, rssItems.get(i).getPubdate());
            data.add(item);
        }
        return data;
    }

    public List<RssFeed> getAll() {
        List<RssFeed> data = new ArrayList<>();
        for (int i = 0; i < rssItems.size(); i++) {
            RssFeed rss = new RssFeed();
            String[] str = rssItems.get(0).getLink().split("/");
            StringBuffer sb = new StringBuffer();
            String name = str[2];
            String[] names = name.split("\\.");
            if (names.length == 4) {
                sb.append("http://").append(names[1] + "." + names[2] + "." + names[3]).append("/favicon.ico");
                rss.setRssname(names[2]);
            } else if (names.length == 3) {
                sb.append("http://").append(names[1] + "." + names[2]).append("/favicon.ico");
                rss.setRssname(names[1]);
            } else if (names.length == 2) {
                sb.append("http://").append(names[0] + "." + names[1]).append("/favicon.ico");
                rss.setRssname(names[0]);
            }
            rss.setCover(sb.toString());
            rss.setTitle(rssItems.get(i).getTitle());
            rss.setPubdate(rssItems.get(i).getPubdate());
            rss.setLink(rssItems.get(i).getLink());
            data.add(rss);
        }
        return data;
    }

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

    public List<RssItem> getRssItems() {
        return rssItems;
    }

    public void setRssItems(List<RssItem> rssItems) {
        this.rssItems = rssItems;
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

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}