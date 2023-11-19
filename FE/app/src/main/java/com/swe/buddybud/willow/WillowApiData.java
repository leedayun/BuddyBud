package com.swe.buddybud.willow;

import com.google.gson.annotations.SerializedName;

public class WillowApiData {
    @SerializedName("nickname")
    private String nickname;

    public String getNickname() {
        return nickname;
    }
}
