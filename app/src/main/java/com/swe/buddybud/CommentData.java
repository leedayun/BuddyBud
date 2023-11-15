package com.swe.buddybud;

public class CommentData {
    private int commentId;
    private int profileImageId;
    private String nickname;
    private String date;
    private String content;
    private String translateContent;
    private int thumbsUpNumber;
    private int thumbsDownNumber;
    private int replyToCommentId;
    private String replyToNickname;
    private boolean isThumbsUpClicked;
    private boolean isThumbsDownClicked;
    private boolean isTranslated;

    // Constructor
    public CommentData(int commentId, int profileImageId, String nickname, String date, String content, String translateContent, int thumbsUpNumber, int thumbsDownNumber, int replyToCommentId, String replyToNickname) {
        this.commentId = commentId;
        this.profileImageId = profileImageId;
        this.nickname = nickname;
        this.date = date;
        this.content = content;
        this.translateContent = translateContent;
        this.thumbsUpNumber = thumbsUpNumber;
        this.thumbsDownNumber = thumbsDownNumber;
        this.replyToCommentId = replyToCommentId;
        this.replyToNickname = replyToNickname;
        this.isThumbsUpClicked = false;
        this.isThumbsDownClicked = false;
        this.isTranslated = false;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getThumbsDownNumber() {
        return thumbsDownNumber;
    }

    public void setThumbsDownNumber(int thumbsDownNumber) {
        this.thumbsDownNumber = thumbsDownNumber;
    }

    public String getReplyToNickname() {
        return replyToNickname;
    }

    public void setReplyToNickname(String replyToNickname) {
        this.replyToNickname = replyToNickname;
    }

    public boolean isThumbsUpClicked() {
        return isThumbsUpClicked;
    }

    public void setThumbsUpClicked(boolean thumbsUpClicked) {
        isThumbsUpClicked = thumbsUpClicked;
    }

    public boolean isThumbsDownClicked() {
        return isThumbsDownClicked;
    }

    public void setThumbsDownClicked(boolean thumbsDownClicked) {
        isThumbsDownClicked = thumbsDownClicked;
    }

    public boolean isTranslated() {
        return isTranslated;
    }

    public void setTranslated(boolean translated) {
        isTranslated = translated;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getReplyToCommentId() {
        return replyToCommentId;
    }

    public void setReplyToCommentId(int replyToCommentId) {
        this.replyToCommentId = replyToCommentId;
    }
}
