package com.platzi.platzigram.model;

import java.io.Serializable;

public class Picture implements Serializable {

    private String picture;
    private String username;
    private String time;
    private long likeNumber;

    public Picture(String picture, String username, String time, long likeNumber) {
        this.picture = picture;
        this.username = username;
        this.time = time;
        this.likeNumber = likeNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(long likeNumber) {
        this.likeNumber = likeNumber;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "picture='" + picture + '\'' +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", likeNumber='" + likeNumber + '\'' +
                '}';
    }
}
