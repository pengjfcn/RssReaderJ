package com.cinread.rss.bean;

import java.util.List;

/**
 * @project：Rss
 * @package：com.cinread.rss.bean
 * @author：pengjf
 * @update：2016/4/15
 * @desc： TODO
 */
// Created by pengjf on 2016/4/15.
public class ResultBean {

    private String hint;
    private String queryType;
    private String scheme;

    private List<ResultsBean> results;
    private List<String>      related;

    public static class ResultsBean {

        private String feedId;
        private String language;
        private String title;
        private double velocity;
        private int    subscribers;
        private long           lastUpdated;
        private String         website;
        private double         score;
        private double         coverage;
        private double         coverageScore;
        private String         scheme;
        private String         contentType;
        private String         description;
        private String         iconUrl;
        private boolean        partial;
        private String         visualUrl;
        private double         art;
        private List< String > deliciousTags;

        public String getFeedId() {
            return feedId;
        }

        public void setFeedId(String feedId) {
            this.feedId = feedId;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getVelocity() {
            return velocity;
        }

        public void setVelocity(double velocity) {
            this.velocity = velocity;
        }

        public int getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(int subscribers) {
            this.subscribers = subscribers;
        }

        public long getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(long lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public double getCoverage() {
            return coverage;
        }

        public void setCoverage(double coverage) {
            this.coverage = coverage;
        }

        public double getCoverageScore() {
            return coverageScore;
        }

        public void setCoverageScore(double coverageScore) {
            this.coverageScore = coverageScore;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public boolean isPartial() {
            return partial;
        }

        public void setPartial(boolean partial) {
            this.partial = partial;
        }

        public String getVisualUrl() {
            return visualUrl;
        }

        public void setVisualUrl(String visualUrl) {
            this.visualUrl = visualUrl;
        }

        public double getArt() {
            return art;
        }

        public void setArt(double art) {
            this.art = art;
        }

        public List<String> getDeliciousTags() {
            return deliciousTags;
        }

        public void setDeliciousTags(List<String> deliciousTags) {
            this.deliciousTags = deliciousTags;
        }
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public List<String> getRelated() {
        return related;
    }

    public void setRelated(List<String> related) {
        this.related = related;
    }
}
