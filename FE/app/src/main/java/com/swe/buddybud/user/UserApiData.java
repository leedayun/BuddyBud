package com.swe.buddybud.user;

import com.google.gson.annotations.SerializedName;

public class UserApiData {

    @SerializedName("userLoginResult")
    private boolean userLoginResult;
    @SerializedName("userEmailDuplCheck")
    private boolean userEmailDuplCheck;

    @SerializedName("userIdDuplCheck")
    private boolean userIdDuplCheck;

    @SerializedName("inserUserInfoResult")
    private String inserUserInfoResult;

    public boolean getUserLoginResult() { return userLoginResult; }

    public boolean getUserEmailDuplCheck(){
        return userEmailDuplCheck;
    }

    public boolean getUserIdDuplCheck(){
        return userIdDuplCheck;
    }

    public String getInserUserInfoResult(){
        return inserUserInfoResult;
    }
}
