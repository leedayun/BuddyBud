package com.swe.buddybud.home;

import android.content.Context;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.swe.buddybud.R;
import com.swe.buddybud.board.BoardApiService;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.user.LoginData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        // 댓글 / 대댓글 recyclerView
        recyclerView = findViewById(R.id.commentRecyclerView);
        // Api 호출
        loadBoardDetails("SNS", LoginData.getLoginUserNo(), feedId);

//
//        // 프로필 사진을 눌렀을 경우
//        profileImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CustomDialog customDialog = CustomDialog.newInstance(feedData.getNickname(), feedData.getProfileImageId());
//                customDialog.show(getSupportFragmentManager(), "customDialog");
//            }
//        });
//
//        // 피드 메인 화면 에서 이미 좋아요 클릭을 했을 경우
//        if (feedData.isThumbsUpClicked()) {
//            thumbsUpImageView.setColorFilter(Color.parseColor("#94DEF7"));
//        } else {
//            thumbsUpImageView.setColorFilter(Color.parseColor("#A4A4A4"));
//        }
//
//        // 좋아요 버튼을 눌렀을 경우
//        thumbsUpImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isClicked = feedData.isThumbsUpClicked();
//                DataManager.getInstance().updateThumbsUp(feedData.getId(), !isClicked);
//
//                if (!isClicked) {
//                    thumbsUpImageView.setColorFilter(Color.parseColor("#94DEF7"));
//                } else {
//                    thumbsUpImageView.setColorFilter(Color.parseColor("#A4A4A4"));
//                }
//                thumbsUpNumberTextView.setText(String.valueOf(feedData.getThumbsUpNumber()));
//            }
//        });
//
//        // 번역 버튼을 눌렀을 경우
//        translationImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                feedData.setTranslated(!feedData.isTranslated());
//
//                if (feedData.isTranslated()) {
//                    Translator translator = new Translator();
//                    translator.detectAndTranslate(feedData.getTitle(), new Translator.TranslationCallback() {
//                        @Override
//                        public void onTranslationDone(String translatedText) {
//                            // 번역된 텍스트 처리
//                            titleTextView.setText(translatedText);
//                        }
//                        @Override
//                        public void onTranslationError(Exception e) {
//                            // 에러 처리
//                            Log.e("Translation", "Error during translation", e);
//                        }
//                    });
//                    translator.detectAndTranslate(feedData.getContent(), new Translator.TranslationCallback() {
//                        @Override
//                        public void onTranslationDone(String translatedText) {
//                            // 번역된 텍스트 처리
//                            contentTextView.setText(translatedText);
//                        }
//                        @Override
//                        public void onTranslationError(Exception e) {
//                            // 에러 처리
//                            Log.e("Translation", "Error during translation", e);
//                        }
//                    });
//                    translationImageView.setColorFilter(Color.parseColor("#94DEF7"));
//                } else {
//                    titleTextView.setText(feedData.getTitle());
//                    contentTextView.setText(feedData.getContent());
//                    translationImageView.setColorFilter(Color.parseColor("#A4A4A4"));
//                }
//            }
//        });
//
//        // 뒤로가기 아이콘을 눌렀을 경우
//        ImageView iconBack = findViewById(R.id.iconBack);
//        iconBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        // 댓글 수 반영
//        int commentCount = feedData.getCommentNumber();
//        numOfComments.setText("Comments(" + commentCount + ")");
//        commentNumberTextView.setText(String.valueOf(commentCount));
//
//        // 이미 댓글을 달았을 경우
//        if (feedData.isCommentWritten()) {
//            commentImageView.setColorFilter(Color.parseColor("#94DEF7")); // 댓글 아이콘 색상 변경
//        }
//
//        // 댓글 달기
//        sendCommentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String commentText = commentEditText.getText().toString();
//                if (!commentText.isEmpty()) {
//                    // 현재 피드에 달린 댓글 수를 기준으로 commentId 생성
//                    int commentId = feedData.getComments().size();
//                    int profileImageId = R.drawable.mili; // 예시 이미지
//                    String nickname = "jinwoo";
//                    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm", Locale.getDefault());
//                    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//                    String currentDate = sdf.format(new Date());
//                    String translateContent = "번역 Test";
//                    int thumbsUpNumber = 0;
//                    int thumbsDownNumber = 0;
//
//                    CommentData newComment;
//                    if (isReplyMode) {
//                        // 대댓글 로직
//                        newComment = new CommentData(commentId, profileImageId, nickname, currentDate, commentText, translateContent, thumbsUpNumber, thumbsDownNumber, replyToCommentId, replyToNickname);
//                    } else {
//                        // 일반 댓글 로직
//                        newComment = new CommentData(commentId, profileImageId, nickname, currentDate, commentText, translateContent, thumbsUpNumber, thumbsDownNumber, commentId, null);
//                    }
//
//                    DataManager.getInstance().addCommentToFeed(feedData.getId(), newComment);
//
//                    // 댓글 작성 상태 업데이트
//                    feedData.setCommentWritten(true);
//                    DataManager.getInstance().updateFeedData(feedData);
//
//                    // 댓글 아이콘 색상 변경
//                    ImageView commentImageView = findViewById(R.id.imageComment); // 댓글 아이콘 ImageView의 ID
//                    commentImageView.setColorFilter(Color.parseColor("#94DEF7"));
//
//                    // 댓글 수 업데이트
//                    int newCommentNumber = feedData.getCommentNumber() + 1;
//                    feedData.setCommentNumber(newCommentNumber);
//
//                    // 댓글 수 TextView 업데이트
//                    TextView feedCommentNumber = findViewById(R.id.feedCommentNumber);
//                    TextView numOfComments = findViewById(R.id.numOfComments); // 댓글 개수를 표시하는 TextView
//                    feedCommentNumber.setText(String.valueOf(newCommentNumber));
//                    numOfComments.setText("Comments(" + newCommentNumber + ")");
//
//                    adapter.notifyDataSetChanged();
//
//                    // EditText 초기화 및 대댓글 모드 해제
//                    resetCommentInput();
//                }
//            }
//            private void resetCommentInput() {
//                isReplyMode = false;
//                replyToCommentId = -1;
//                replyToNickname = null;
//                commentEditText.setHint("");
//                commentEditText.setText("");
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(commentEditText.getWindowToken(), 0);
//            }
//        });
//
//        // EditText 포커스 리스너 추가
//        commentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    // 대댓글 모드 해제 및 힌트 초기화
//                    isReplyMode = false;
//                    replyToCommentId = -1;
//                    replyToNickname = null;
//                    commentEditText.setHint("");
//                    commentEditText.setText("");
//                }
//            }
//        });

//        RecyclerView imageRecyclerView = findViewById(R.id.imageRecyclerView);
//        List<Uri> imageUris = feedData.getImageUris();
//        if (imageUris != null && !imageUris.isEmpty()) {
//            // 이미지 URI 리스트를 사용하여 RecyclerView 초기화
//            ImageAdapter imageAdapter = new ImageAdapter(imageUris);
//            imageRecyclerView.setAdapter(imageAdapter);
//            imageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//            imageRecyclerView.setVisibility(View.VISIBLE);
//        }
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

    private void loadBoardDetails(String boardType, Integer userId, Integer boardId) {
        BoardApiService boardApiServices = RetrofitClient.getService(BoardApiService.class);
        Call<JsonObject> call = boardApiServices.getBoardDetails(boardType, userId, boardId);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject boardDetails = response.body();

                    // 피드 UI 업데이트
                    updateUIWithBoardDetails(boardDetails);
                    
                    // 댓글 데이터 추출 및 변환
                    commentDataList = new ArrayList<>();
                    JsonArray commentsJsonArray = boardDetails.getAsJsonArray("comments");
                    for (JsonElement commentElement : commentsJsonArray) {
                        JsonObject commentObject = commentElement.getAsJsonObject();
                        CommentData commentData = new CommentData(
                                commentObject.get("comment_no").getAsInt(),
                                R.drawable.logo_profile, // 프로필 이미지
                                commentObject.get("user_id").getAsString(),
                                commentObject.get("created_at").getAsString(),
                                commentObject.get("content").getAsString(),
                                0, // thumbsUpNumber
                                0, // thumbsDownNumber
                                -1, // replyToCommentId
                                null  // replyToNickname
                        );
                        commentDataList.add(commentData);
                    }

                    // 어댑터 설정 및 갱신
                    adapter = new CommentAdapter(commentDataList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(FeedDetailActivity.this));
                    recyclerView.addItemDecoration(new RecycleViewDecoration(15));
                    
                } else {
                    Log.d("FeedDetail", "Server error");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("FeedDetail", "Network error: " + t.getMessage());
            }
        });
    }

    private void updateUIWithBoardDetails(JsonObject boardDetails) {
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

        //임시
        profileImageView.setImageResource(R.drawable.logo_profile);
        nicknameTextView.setText("temp_nickname");

        dateTextView.setText(boardDetails.get("created_at").getAsString());
        titleTextView.setText(boardDetails.get("title").getAsString());
        contentTextView.setText(boardDetails.get("content").getAsString());
        thumbsUpNumberTextView.setText(String.valueOf(boardDetails.get("like_num").getAsInt()));
        commentNumberTextView.setText(String.valueOf(boardDetails.get("comment_num").getAsInt()));
    }
}