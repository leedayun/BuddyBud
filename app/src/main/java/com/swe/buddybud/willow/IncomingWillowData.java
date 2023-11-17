package com.swe.buddybud.willow;

import androidx.annotation.NonNull;

public class IncomingWillowData {
    IncomingWillowData(String userId, String dept, String gender, int imgResId){
        this.userId = userId;
        this.dept = dept;
        this.gender = gender;
        this.imgResId = imgResId;
    }
    private String userId;
    private String dept;
    private String gender;
    private int imgResId;

    public String getUserId() {
        return userId;
    }

    public String getDept() {
        return dept;
    }

    public String getGender() {
        return gender;
    }

    public int getImgResId() {
        return imgResId;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("userid : %s dept : %s gender %s", userId, dept, gender);
    }
}
