package com.cinread.rss;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cinread.rss.adapter.CollectAdapter;
import com.cinread.rss.adapter.RssFeedAdapter;
import com.cinread.rss.adapter.SubscribeAdapter;
import com.cinread.rss.base.UIUtils;
import com.cinread.rss.bean.CollectInfo;
import com.cinread.rss.bean.RssFeed;
import com.cinread.rss.bean.RssItem;
import com.cinread.rss.bean.SubscribeInfo;
import com.cinread.rss.factory.RssFeedParser;
import com.cinread.rss.ui.AddFeedActivity;
import com.cinread.rss.ui.RssReadActivity;
import com.cinread.rss.utils.FileUtils;
import com.cinread.rss.utils.LogUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "MainActivity";

    enum DataTypeMode {
        HOME, SUBSCRIBE, COLLECT
    }

    private DataTypeMode mDataType;

    private ImageView           mIvMenu;
    private ImageView           mIvMore;
    private TextView            mTvTitle;
    private TextView            mTvLoading;
    private ListView            mListView;
    private List<RssFeed>       mRssFeeds;
    private List<RssFeed>       mAllRssDatas;
    private List<CollectInfo>   mCollectDatas;
    private List<SubscribeInfo> mSubscribeDatas;
    private boolean             flag;
    private boolean first = true;
    private DbUtils db    = UIUtils.getDbManager();
    private RssFeed feed  = null;
    private String cacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.main_tv_title);
        mTvLoading = (TextView) findViewById(R.id.loading);
        mIvMenu = (ImageView) findViewById(R.id.main_iv_menu);
        mIvMore = (ImageView) findViewById(R.id.main_iv_more);
        mListView = (ListView) findViewById(R.id.listview);
    }

    private void initData() {
        if (first) {
            feedRssData(true, null, mRssFeeds);
            first = false;
        }
    }

    private void feedRssData(final boolean showAll, final String url, final List<RssFeed> data) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mListView.setVisibility(View.GONE);
                mTvLoading.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    List<SubscribeInfo> all = db.findAll(Selector.from(SubscribeInfo.class));
                    if (data == null) {
                        if (showAll && all != null) {
                            mRssFeeds = new ArrayList<>();
                            for (int i = 0; i < all.size(); i++) {
                                String baseurl = all.get(i).getUrl();
                                int code = isSuccess(baseurl);
                                if (code == 200) { //连接成功时才do work
                                    LogUtils.d(TAG, "连接成功");
                                    feed = new RssFeedParser().getFeed(baseurl);
                                    List<RssItem> rssItems = feed.getRssItems();
                                    for (int j = 0; j < rssItems.size(); j++) {
                                        RssFeed rss = new RssFeed();
                                        String[] str = rssItems.get(0).getLink().split("/");
                                        StringBuffer sb = new StringBuffer();
                                        String name = null;
                                        if (str.length >= 2) {
                                            name = str[2];
                                        } else {
                                            name = str[1];
                                        }
                                        String[] names = name.split("\\.");
                                        //sb.append("http://").append(names[1] + "." + names[2]).append("/favicon.ico");
                                        if (names.length == 4) {
                                            sb.append("http://").append(names[1] + "." + names[2] + "." + names[3]).append("/favicon.ico");
                                        } else if (names.length == 3) {
                                            sb.append("http://").append(names[1] + "." + names[2]).append("/favicon.ico");
                                        } else if (names.length == 2) {
                                            sb.append("http://").append(names[0] + "." + names[1]).append("/favicon.ico");
                                        }
                                        rss.setCover(sb.toString());
                                        rss.setTitle(rssItems.get(j).getTitle());
                                        rss.setPubdate(FileUtils.getTime3(rssItems.get(j).getPubdate()));
                                        rss.setRssname(FileUtils.changeName(names[1]));
                                        rss.setLink(rssItems.get(j).getLink());
                                        mRssFeeds.add(rss);
                                        db.replace(rss);
                                    }
                                }
                            }
                            mAllRssDatas = db.findAll(Selector.from(RssFeed.class).orderBy("pubDate", true));
                        } else {
                            if (isSuccess(url) == 200) {
                                feed = new RssFeedParser().getFeed(url);
                                mAllRssDatas = feed.getAll();
                            }
                        }
                    } else {
                        mAllRssDatas = data;
                    }
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (feed == null) {
                    return;
                }
                mListView.setAdapter(new RssFeedAdapter(mAllRssDatas, db));
                mDataType = DataTypeMode.HOME;
                mTvLoading.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
            }
        }.execute();
    }

    private int isSuccess(String baseurl) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(baseurl).openConnection();
        conn.setConnectTimeout(3000);
        return conn.getResponseCode();
    }

    private void initEvent() {
        mIvMenu.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mIvMenu) {
            View popuView = View.inflate(UIUtils.getContext(), R.layout.item_menu, null);
            final PopupWindow popupWindow = new PopupWindow(popuView, 120, 120);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAsDropDown(v);
            popuView.findViewById(R.id.menu_tv_feed_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddFeedActivity.class);
                    startActivity(intent);
                    popupWindow.dismiss();
                }
            });
            popuView.findViewById(R.id.menu_tv_feed_all).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    feedRssData(true, null, null);
                    mDataType = DataTypeMode.HOME;
                    mTvTitle.setText("Rss");
                    popupWindow.dismiss();
                }
            });
        } else if (v == mIvMore) {
            View popuView = View.inflate(UIUtils.getContext(), R.layout.item_more, null);
            final PopupWindow popupWindow = new PopupWindow(popuView, 120, 200);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAsDropDown(v);
            TextView tvOpen = (TextView) popuView.findViewById(R.id.more_tv_open);
            TextView tvClose = (TextView) popuView.findViewById(R.id.more_tv_close);
            TextView tvSize = (TextView) popuView.findViewById(R.id.more_tv_clear);
            tvSize.setText(cacheSize);
            tvOpen.setVisibility(flag ? View.VISIBLE : View.GONE);
            tvClose.setVisibility(!flag ? View.VISIBLE : View.GONE);
            popuView.findViewById(R.id.more_tv_collect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mCollectDatas = db.findAll(Selector.from(CollectInfo.class).orderBy("time", true));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    mListView.setAdapter(new CollectAdapter(mCollectDatas));
                    mDataType = DataTypeMode.COLLECT;
                    mTvTitle.setText("我的收藏");
                    popupWindow.dismiss();
                }
            });
            popuView.findViewById(R.id.more_tv_subscribe).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mSubscribeDatas = db.findAll(Selector.from(SubscribeInfo.class).orderBy("title", false));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    mListView.setAdapter(new SubscribeAdapter(mSubscribeDatas));
                    mDataType = DataTypeMode.SUBSCRIBE;
                    mTvTitle.setText("我的订阅");
                    popupWindow.dismiss();
                }
            });
            popuView.findViewById(R.id.more_tv_download).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), !flag ? "开启" : "关闭", Toast.LENGTH_SHORT).show();
                    flag = !flag;
                    popupWindow.dismiss();
                }
            });
            tvSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), "清除", Toast.LENGTH_SHORT).show();
                    FileUtils.delFile(FileUtils.getCacheDir());
                    popupWindow.dismiss();
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mDataType == DataTypeMode.HOME) {
            //进入阅读
            TextView tv = (TextView) view.findViewById(R.id.rss_tv_title);
            String title = (String) tv.getText();
            try {
                mRssFeeds = db.findAll(Selector.from(RssFeed.class).where("title", " = ", title));
            } catch (DbException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(MainActivity.this, RssReadActivity.class);
            intent.putExtra("url", mRssFeeds.get(0).getLink());
            startActivity(intent);
        } else if (mDataType == DataTypeMode.COLLECT) {
            //进入阅读
            TextView tv = (TextView) view.findViewById(R.id.collect_tv_title);
            String title = (String) tv.getText();
            try {
                mRssFeeds = db.findAll(Selector.from(RssFeed.class).where("title", " = ", title));
            } catch (DbException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(MainActivity.this, RssReadActivity.class);
            intent.putExtra("url", mRssFeeds.get(0).getLink());
            startActivity(intent);
        } else if (mDataType == DataTypeMode.SUBSCRIBE) {
            TextView tvTitle = (TextView) view.findViewById(R.id.subscribe_tv_title);
            String rssname = (String) tvTitle.getText();
            try {
                mRssFeeds = db.findAll(Selector.from(RssFeed.class).where("rssname", " = ", rssname).orderBy("pubDate", true));
            } catch (DbException e) {
                e.printStackTrace();
            }
            feedRssData(false, null, mRssFeeds);
            mDataType = DataTypeMode.HOME;
            mTvTitle.setText("Rss");
        }
    }

    private long exitTime = 0;

    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), R.string.twice_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        File file = new File(FileUtils.getCacheDir());
        long length = file.length();
        cacheSize = Math.round((float) length / 1024) * 0.01 + "Kb";
        mDataType = DataTypeMode.HOME;
        mTvTitle.setText("Rss");
        try {
            mRssFeeds = db.findAll(Selector.from(RssFeed.class).orderBy("pubDate", true));
        } catch (DbException e) {
            e.printStackTrace();
        }
        feedRssData(true, null, mRssFeeds);
    }
}