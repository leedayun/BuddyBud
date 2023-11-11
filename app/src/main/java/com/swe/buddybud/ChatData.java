package com.swe.buddybud;

import java.time.LocalTime;

public class ChatData {
    private LocalTime timestamp;
    private String message;
    private String name;
    private int imgResId;

    public ChatData(LocalTime timestamp, String message, String name, int imgResId) {
        this.timestamp = timestamp;
        this.message = message;
        this.name = name;
        this.imgResId = imgResId;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public int getImgResId() {
        return imgResId;
    }
}
