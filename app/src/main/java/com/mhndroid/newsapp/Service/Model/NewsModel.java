package com.mhndroid.newsapp.Service.Model;

public class NewsModel {
    private String title;
    private String by;
    private long time;
    private String url;

    public NewsModel(String title, String by, long time, String url) {
        this.title = title;
        this.by = by;
        this.time = time;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
