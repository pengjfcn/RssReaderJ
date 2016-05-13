package com.cinread.rss.ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cinread.rss.base.UIUtils;

/**
 * @project：Rss
 * @package：com.cinread.rss.ui
 * @author：pengjf
 * @update：2016/4/14
 * @desc： TODO
 */
// Created by pengjf on 2016/4/14.
public class RssReadActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra("url");
        mWebView = new WebView(UIUtils.getContext());
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAppCachePath(UIUtils.getAppFile() + "/WEBCaches/");
        ws.setSaveFormData(true);       //保存
        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        mWebView.loadUrl(url);
        setContentView(mWebView);
    }
}
