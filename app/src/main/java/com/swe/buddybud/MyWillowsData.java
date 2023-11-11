package com.swe.buddybud;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class MyWillowsData {
    public MyWillowsData(String userId, LocalDateTime lastMsgTime, String lastMsg, int imgResId) {
        this.userId = userId;
        this.lastMsgTime = lastMsgTime;
        this.lastMsg = lastMsg;
        this.imgResId = imgResId;
    }

    private String userId;
    private LocalDateTime lastMsgTime;
    private String lastMsg;
    private int imgResId;

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getLastMsgTime() {
        return lastMsgTime;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public int getImgResId() {
        return imgResId;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("userid : %s lastmsg: %s", userId, lastMsg);
    }
}
