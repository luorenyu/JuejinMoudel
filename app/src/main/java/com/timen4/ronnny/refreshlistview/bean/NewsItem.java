package com.timen4.ronnny.refreshlistview.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luore on 2016/8/21.
 */
public class NewsItem {
    int Image;
    String title;
    int likes;
    String author;
    long timeStamp;
    boolean isoffline;


    public NewsItem(int image, String title, int likes, String author, long timeStamp, boolean isoffline) {
        Image = image;
        this.title = title;
        this.likes = likes;
        this.author = author;
        this.timeStamp = timeStamp;
        this.isoffline = isoffline;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isoffline() {
        return isoffline;
    }

    public void setIsoffline(boolean isoffline) {
        this.isoffline = isoffline;
    }

    public String getArtcileInfo(){
        String info = null;


        return info;
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long infoTime) {
        long currentTimeMillis = System.currentTimeMillis();
        Date curData =new Date(currentTimeMillis);

        return "";
    }



}
