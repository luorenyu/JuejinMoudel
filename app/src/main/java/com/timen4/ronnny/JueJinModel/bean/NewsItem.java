package com.timen4.ronnny.JueJinModel.bean;

import com.timen4.ronnny.JueJinModel.util.DateUtils;

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

    public NewsItem(int image, String title, int likes, String author, long timeStamp, boolean isOffline) {
        this.Image = image;
        this.title = title;
        this.likes = likes;
        this.author = author;
        this.timeStamp = timeStamp;
        this.isoffline = isOffline;
    }

    public NewsItem(String title) {
        this.Image = 0;
        this.title = title;
        this.likes = 0;
        this.author = "";
        this.timeStamp = 0;
        this.isoffline = false;
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
        if(isoffline){
            info=getLikes()+"人收藏 · "+getAuthor()+" · "+ DateUtils.getGapTimeFromNow(timeStamp)+" · 已离线";
        }else{
            info=getLikes()+"人收藏 · "+getAuthor()+" · "+DateUtils.getGapTimeFromNow(timeStamp);
        }
        return info;
    }


}
