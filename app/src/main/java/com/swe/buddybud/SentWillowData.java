package com.swe.buddybud;

import androidx.annotation.NonNull;

public class SentWillowData {
    SentWillowData(String userId, int imgResId){
        this.userId = userId;
        this.imgResId = imgResId;
    }
    private String userId;
    private int imgResId;

    public String getUserId() {
        return userId;
    }

    public int getImgResId() {
        return imgResId;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("sent userid : %s", userId);
    }
}
