package com.cinread.rss.factory;

import com.cinread.rss.base.UIUtils;
import com.lidroid.xutils.DbUtils;

/**
 * @project：File
 * @package：com.cinread.rss.base
 * @author：pengjf
 * @update：2016/4/11
 * @desc： TODO
 */
// Created by pengjf on 2016/3/16.
public class DBFactory {

    public static DbUtils getDb() {
        DbUtils db = DbUtils.create(UIUtils.getContext(), UIUtils.getAppFile().getPath(), "rss.db", 1, new DbUtils.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils dbUtils, int i, int i1) {

            }
        });
        return db;
    }
}
