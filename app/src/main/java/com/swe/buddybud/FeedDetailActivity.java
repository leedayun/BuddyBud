package com.swe.buddybud;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private List<CommentData> commentDataList;
    private boolean isReplyMode = false;
    private int replyToCommentId = -1;
    private String replyToNickname = null;

    // 대댓글 모드 활성화 메서드
    public void enableReplyMode(int commentId, String nickname) {
        this.isReplyMode = true;
        this.replyToCommentId = commentId;
        this.replyToNickname = nickname;

        EditText commentEditText = findViewById(R.id.commentEditText);
        commentEditText.setHint("@ " + nickname + " write reply");
        commentEditText.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        int feedId = getIntent().getIntExtra("feed_id", -1);
        FeedData feedData = findFeedById(feedId);

        CircleImageView profileImageView = findViewById(R.id.feedProfileImage);
        TextView nicknameTextView = findViewById(R.id.feedNickname);
        TextView dateTextView = findViewById(R.id.feedDate);
        TextView titleTextView = findViewById(R.id.feedTitle);
        TextView contentTextView = findViewById(R.id.feedContent);
        ImageView thumbsUpImageView = findViewById(R.id.imageThumbsUp);
        TextView thumbsUpNumberTextView = findViewById(R.id.feedThumbsUpNumber);
        ImageView commentImageView = findViewById(R.id.imageComment);
        TextView commentNumberTextView = findViewById(R.id.feedCommentNumber);
        TextView numOfComments = findViewById(R.id.numOfComments);
        ImageView translationImageView = findViewById(R.id.imageTranslation);
        EditText commentEditText = findViewById(R.id.commentEditText);
        ImageView sendCommentButton = findViewById(R.id.sendCommentButton);

        profileImageView.setImageResource(feedData.getProfileImageId());
        nicknameTextView.setText(feedData.getNickname());
        dateTextView.setText(feedData.getDate());
        titleTextView.setText(feedData.getTitle());
        contentTextView.setText(feedData.getContent());
        thumbsUpNumberTextView.setText(String.valueOf(feedData.getThumbsUpNumber()));
        commentNumberTextView.setText(String.valueOf(feedData.getCommentNumber()));

        // 프로필 사진을 눌렀을 경우
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = CustomDialog.newInstance(feedData.getNickname(), feedData.getProfileImageId());
                customDialog.show(getSupportFragmentManager(), "customDialog");
            }
        });

        // 피드 메인 화면 에서 이미 좋아요 클릭을 했을 경우
        if (feedData.isThumbsUpClicked()) {
            thumbsUpImageView.setColorFilter(Color.parseColor("#94DEF7"));
        } else {
            thumbsUpImageView.setColorFilter(Color.parseColor("#A4A4A4"));
        }

        // 좋아요 버튼을 눌렀을 경우
        thumbsUpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isClicked = feedData.isThumbsUpClicked();
                DataManager.getInstance().updateThumbsUp(feedData.getId(), !isClicked);

                if (!isClicked) {
                    thumbsUpImageView.setColorFilter(Color.parseColor("#94DEF7"));
                } else {
                    thumbsUpImageView.setColorFilter(Color.parseColor("#A4A4A4"));
                }
                thumbsUpNumberTextView.setText(String.valueOf(feedData.getThumbsUpNumber()));
            }
        });

        // 번역 버튼을 눌렀을 경우
        translationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedData.setTranslated(!feedData.isTranslated());

                if (feedData.isTranslated()) {
                    titleTextView.setText(feedData.getTranslateTitle());
                    contentTextView.setText(feedData.getTranslateContent());
                    translationImageView.setColorFilter(Color.parseColor("#94DEF7"));
                } else {
                    titleTextView.setText(feedData.getTitle());
                    contentTextView.setText(feedData.getContent());
                    translationImageView.setColorFilter(Color.parseColor("#A4A4A4"));
                }
            }
        });

        // 뒤로가기 아이콘을 눌렀을 경우
        ImageView iconBack = findViewById(R.id.iconBack);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 댓글 수 반영
        int commentCount = feedData.getCommentNumber();
        numOfComments.setText("Comments(" + commentCount + ")");
        commentNumberTextView.setText(String.valueOf(commentCount));

        // 이미 댓글을 달았을 경우
        if (feedData.isCommentWritten()) {
            commentImageView.setColorFilter(Color.parseColor("#94DEF7")); // 댓글 아이콘 색상 변경
        }

        // 댓글 달기
        sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = commentEditText.getText().toString();
                if (!commentText.isEmpty()) {
                    // 현재 피드에 달린 댓글 수를 기준으로 commentId 생성
                    int commentId = feedData.getComments().size();
                    int profileImageId = R.drawable.test; // 예시 이미지
                    String nickname = "MyNick";
                    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm", Locale.getDefault());
                    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                    String currentDate = sdf.format(new Date());
                    String translateContent = "번역 Test";
                    int thumbsUpNumber = 0;
                    int thumbsDownNumber = 0;

                    CommentData newComment;
                    if (isReplyMode) {
                        // 대댓글 로직
                        newComment = new CommentData(commentId, profileImageId, nickname, currentDate, commentText, translateContent, thumbsUpNumber, thumbsDownNumber, replyToCommentId, replyToNickname);
                    } else {
                        // 일반 댓글 로직
                        newComment = new CommentData(commentId, profileImageId, nickname, currentDate, commentText, translateContent, thumbsUpNumber, thumbsDownNumber, commentId, null);
                    }

                    DataManager.getInstance().addCommentToFeed(feedData.getId(), newComment);

                    // 댓글 작성 상태 업데이트
                    feedData.setCommentWritten(true);
                    DataManager.getInstance().updateFeedData(feedData);

                    // 댓글 아이콘 색상 변경
                    ImageView commentImageView = findViewById(R.id.imageComment); // 댓글 아이콘 ImageView의 ID
                    commentImageView.setColorFilter(Color.parseColor("#94DEF7"));

                    // 댓글 수 업데이트
                    int newCommentNumber = feedData.getCommentNumber() + 1;
                    feedData.setCommentNumber(newCommentNumber);

                    // 댓글 수 TextView 업데이트
                    TextView feedCommentNumber = findViewById(R.id.feedCommentNumber);
                    TextView numOfComments = findViewById(R.id.numOfComments); // 댓글 개수를 표시하는 TextView
                    feedCommentNumber.setText(String.valueOf(newCommentNumber));
                    numOfComments.setText("Comments(" + newCommentNumber + ")");

                    adapter.notifyDataSetChanged();

                    // EditText 초기화 및 대댓글 모드 해제
                    resetCommentInput();
                }
            }
            private void resetCommentInput() {
                isReplyMode = false;
                replyToCommentId = -1;
                replyToNickname = null;
                commentEditText.setHint("");
                commentEditText.setText("");

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(commentEditText.getWindowToken(), 0);
            }
        });

        // EditText 포커스 리스너 추가
        commentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 대댓글 모드 해제 및 힌트 초기화
                    isReplyMode = false;
                    replyToCommentId = -1;
                    replyToNickname = null;
                    commentEditText.setHint("");
                    commentEditText.setText("");
                }
            }
        });

        // 댓글 / 대댓글 recyclerView
        recyclerView = findViewById(R.id.commentRecyclerView);
        commentDataList = feedData.getComments();
        adapter = new CommentAdapter(commentDataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDecoration(15));

        RecyclerView imageRecyclerView = findViewById(R.id.imageRecyclerView);
        List<Uri> imageUris = feedData.getImageUris();
        if (imageUris != null && !imageUris.isEmpty()) {
            // 이미지 URI 리스트를 사용하여 RecyclerView 초기화
            ImageAdapter imageAdapter = new ImageAdapter(imageUris);
            imageRecyclerView.setAdapter(imageAdapter);
            imageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            imageRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private FeedData findFeedById(int feedId) {
        List<FeedData> feedList = DataManager.getInstance().getFeedList();
        for (FeedData feed : feedList) {
            if (feed.getId() == feedId) {
                return feed;
            }
        }
        return null;
    }
}