package com.swe.buddybud;

import android.net.Uri;

import java.time.LocalTime;

public class ChatData {
    private LocalTime timestamp;
    private String message;
    private String name;
    private int imgResId;
    private Uri imageURI;

    public ChatData(LocalTime timestamp, String message, String name, int imgResId, Uri imageURI) {
        this.timestamp = timestamp;
        if(imageURI!=null) {
            this.message = "";
            this.imageURI = imageURI;
        }
        else {
            this.message = message.trim();
            this.imageURI = null;
        }
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
    public Uri getImageURI() {
        return imageURI;
    }
}
