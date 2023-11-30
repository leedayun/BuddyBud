package com.swe.buddybud.board;

import android.net.Uri;

import java.io.Serializable;
import java.util.List;

public class BoardData implements Serializable {
    private int id;
    private int profileImageId;
    private String nickname;
    private String date;
    private String title;
    private String content;
    private int thumbsUpNumber;
    private int scrapNumber;
    private boolean isThumbsUpClicked;
    private boolean isScrapClicked;
    private boolean isTranslated;
    public BoardData(int id, int profileImageId, String nickname, String date, String title, String content,
                    int thumbsUpNumber, int scrapNumber) {
        this.id = id;
        this.profileImageId = profileImageId;
        this.nickname = nickname;
        this.date = date;
        this.title = title;
        this.content = content;
        this.thumbsUpNumber = thumbsUpNumber;
        this.scrapNumber = scrapNumber;
        this.isThumbsUpClicked = false;
        this.isTranslated = false;
    }
    public int getId() {
        return id;
    }

    public int getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(int profileImageId) {
        this.profileImageId = profileImageId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getThumbsUpNumber() {
        return thumbsUpNumber;
    }

    public void setThumbsUpNumber(int thumbsUpNumber) {
        this.thumbsUpNumber = thumbsUpNumber;
    }

    public int getScrapNumber() {
        return scrapNumber;
    }

    public void setScrapNumber(int scrapNumber) {
        this.scrapNumber = scrapNumber;
    }

    public boolean isThumbsUpClicked() {
        return isThumbsUpClicked;
    }

    public void setThumbsUpClicked(boolean thumbsUpClicked) {
        isThumbsUpClicked = thumbsUpClicked;
    }

    public boolean isTranslated() {
        return isTranslated;
    }

    public void setTranslated(boolean translated) {
        isTranslated = translated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isScrapClicked() {
        return isScrapClicked;
    }

    public void setScrapClicked(boolean isScrapClicked) {
        isScrapClicked = isScrapClicked;
    }
}