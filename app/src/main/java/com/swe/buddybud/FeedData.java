package com.swe.buddybud;

import android.media.Image;
import android.net.Uri;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.List;

public class FeedData implements Serializable {
    private int id;
    private int profileImageId;
    private String nickname;
    private String date;
    private String title;
    private String content;
    private String translateTitle;
    private String translateContent;
    private int thumbsUpNumber;
    private int commentNumber;
    private boolean isThumbsUpClicked;
    private boolean isCommentWritten;
    private boolean isTranslated;
    private List<CommentData> comments;
    private List<Uri> imageUris;
    public FeedData(int id, int profileImageId, String nickname, String date, String title, String content, String translateTitle, String translateContent,
                    int thumbsUpNumber, int commentNumber, List<CommentData> comments, List<Uri> imageUris) {
        this.id = id;
        this.profileImageId = profileImageId;
        this.nickname = nickname;
        this.date = date;
        this.title = title;
        this.content = content;
        this.translateTitle = translateTitle;
        this.translateContent = translateContent;
        this.thumbsUpNumber = thumbsUpNumber;
        this.commentNumber = commentNumber;
        this.isThumbsUpClicked = false;
        this.isTranslated = false;
        this.comments = comments;
        this.imageUris = imageUris;
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

    public String getTranslateTitle() {
        return translateTitle;
    }

    public void setTranslateTitle(String translateTitle) {
        this.translateTitle = translateTitle;
    }

    public String getTranslateContent() {
        return translateContent;
    }

    public void setTranslateContent(String translateContent) {
        this.translateContent = translateContent;
    }

    public int getThumbsUpNumber() {
        return thumbsUpNumber;
    }

    public void setThumbsUpNumber(int thumbsUpNumber) {
        this.thumbsUpNumber = thumbsUpNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
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

    public List<CommentData> getComments() {
        return comments;
    }

    public void addComments(CommentData comment) {
        comments.add(comment);
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCommentWritten() {
        return isCommentWritten;
    }

    public void setCommentWritten(boolean commentWritten) {
        isCommentWritten = commentWritten;
    }

    public List<Uri> getImageUris() {
        return imageUris;
    }

    public void setImageUris(List<Uri> imageUris) {
        this.imageUris = imageUris;
    }
}