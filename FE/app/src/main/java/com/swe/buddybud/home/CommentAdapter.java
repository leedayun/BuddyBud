package com.swe.buddybud.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.swe.buddybud.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommentData> commentDataList;
    private static final int TYPE_COMMENT = 0;
    private static final int TYPE_REPLY = 1;
    public CommentAdapter(List<CommentData> commentDataList) {
        this.commentDataList = commentDataList;
    }

    @Override
    public int getItemViewType(int position) {
        // replyToNickname이 null이 아니면 대댓글로 간주
        CommentData item = commentDataList.get(position);
        if (item.getReplyToNickname() != null) {
            return TYPE_REPLY;
        } else {
            return TYPE_COMMENT;
        }
    }

    // 댓글 뷰 홀더
    public class CommentViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        ImageView imageThumbsUp, imageThumbsDown, imageTranslation, imageRecomment;
        public TextView nickname, date, content, thumbsUpNumber, thumbsDownNumber;

        public CommentViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.commentProfileImage);
            nickname = itemView.findViewById(R.id.commentNickname);
            date = itemView.findViewById(R.id.commentDate);
            content = itemView.findViewById(R.id.commentText);
            thumbsUpNumber = itemView.findViewById(R.id.commentThumbsUpNumber);
            thumbsDownNumber = itemView.findViewById(R.id.feedCommentNumber);
            imageThumbsUp = itemView.findViewById(R.id.imageThumbsUp);
            imageThumbsDown = itemView.findViewById(R.id.imageThumbsDown);
            imageTranslation = itemView.findViewById(R.id.imageTranslation);
            imageRecomment = itemView.findViewById(R.id.imageRecomment);
        }
    }

    // 대댓글 뷰 홀더
    public class ReplyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        ImageView imageThumbsUp, imageThumbsDown, imageTranslation, imageRecomment;
        public TextView nickname, date, content, thumbsUpNumber, thumbsDownNumber, replyNickname;

        public ReplyViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.commentProfileImage);
            nickname = itemView.findViewById(R.id.commentNickname);
            replyNickname = itemView.findViewById(R.id.commentReplyNickname);
            date = itemView.findViewById(R.id.commentDate);
            content = itemView.findViewById(R.id.commentText);
            thumbsUpNumber = itemView.findViewById(R.id.commentThumbsUpNumber);
            thumbsDownNumber = itemView.findViewById(R.id.feedCommentNumber);
            imageThumbsUp = itemView.findViewById(R.id.imageThumbsUp);
            imageThumbsDown = itemView.findViewById(R.id.imageThumbsDown);
            imageTranslation = itemView.findViewById(R.id.imageTranslation);
            imageRecomment = itemView.findViewById(R.id.imageRecomment);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_COMMENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
            return new CommentViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_reply, parent, false);
            return new ReplyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentData commentData = commentDataList.get(position);

        if (holder.getItemViewType() == TYPE_COMMENT) {
            CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
            commentViewHolder.profileImage.setImageResource(commentData.getProfileImageId());;
            commentViewHolder.nickname.setText(commentData.getNickname());;
            commentViewHolder.date.setText(commentData.getDate());;
            commentViewHolder.content.setText(commentData.getContent());;
            commentViewHolder.thumbsUpNumber.setText(String.valueOf(commentData.getThumbsUpNumber()));;
            commentViewHolder.thumbsDownNumber.setText(String.valueOf(commentData.getThumbsDownNumber()));;

            // 프로필 사진을 눌렀을 경우
            commentViewHolder.profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    if (context instanceof AppCompatActivity) {
                        CustomDialog customDialog = CustomDialog.newInstance(commentData.getNickname(), commentData.getProfileImageId());
                        customDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "customDialog");
                    }
                }
            });

            // 댓글 좋아요 버튼을 눌렀을 경우
            commentViewHolder.imageThumbsUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = commentViewHolder.getAdapterPosition();
                    CommentData currentData = commentDataList.get(adapterPosition);

                    boolean isClicked = currentData.isThumbsUpClicked();
                    currentData.setThumbsUpClicked(!isClicked);

                    if (!isClicked) {
                        commentViewHolder.imageThumbsUp.setColorFilter(Color.parseColor("#94DEF7"));
                        commentData.setThumbsUpNumber(commentData.getThumbsUpNumber() + 1);
                    } else {
                        commentViewHolder.imageThumbsUp.setColorFilter(Color.parseColor("#A4A4A4"));
                        commentData.setThumbsUpNumber(commentData.getThumbsUpNumber() - 1);
                    }
                    commentViewHolder.thumbsUpNumber.setText(String.valueOf(currentData.getThumbsUpNumber()));
                }
            });

            // 댓글 싫어요 버튼을 눌렀을 경우
            commentViewHolder.imageThumbsDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = commentViewHolder.getAdapterPosition();
                    CommentData currentData = commentDataList.get(adapterPosition);

                    boolean isClicked = currentData.isThumbsDownClicked();
                    currentData.setThumbsDownClicked(!isClicked);

                    if (!isClicked) {
                        commentViewHolder.imageThumbsDown.setColorFilter(Color.parseColor("#94DEF7"));
                        commentData.setThumbsDownNumber(commentData.getThumbsDownNumber() + 1);
                    } else {
                        commentViewHolder.imageThumbsDown.setColorFilter(Color.parseColor("#A4A4A4"));
                        commentData.setThumbsDownNumber(commentData.getThumbsDownNumber() - 1);
                    }
                    commentViewHolder.thumbsDownNumber.setText(String.valueOf(currentData.getThumbsDownNumber()));
                }
            });

            // 대댓글 번역 아이콘을 눌렀을 경우
            commentViewHolder.imageTranslation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommentData data = commentDataList.get(holder.getAdapterPosition());

                    data.setTranslated(!data.isTranslated());

                    if (data.isTranslated()) {
                        commentViewHolder.content.setText(data.getTranslateContent());
                        commentViewHolder.imageTranslation.setColorFilter(Color.parseColor("#94DEF7"));
                    } else {
                        commentViewHolder.content.setText(data.getContent());
                        commentViewHolder.imageTranslation.setColorFilter(Color.parseColor("#A4A4A4"));
                    }
                }
            });

            // reply 이미지를 눌렀을 경우
            commentViewHolder.imageRecomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    if (context instanceof FeedDetailActivity) {
                        FeedDetailActivity activity = (FeedDetailActivity) context;
                        activity.enableReplyMode(commentData.getReplyToCommentId(), commentData.getNickname());
                    }
                }
            });


        } else {
            ReplyViewHolder replyViewHolder = (ReplyViewHolder) holder;
            replyViewHolder.profileImage.setImageResource(commentData.getProfileImageId());;
            replyViewHolder.nickname.setText(commentData.getNickname());;
            replyViewHolder.replyNickname.setText(commentData.getReplyToNickname());
            replyViewHolder.date.setText(commentData.getDate());;
            replyViewHolder.content.setText(commentData.getContent());;
            replyViewHolder.thumbsUpNumber.setText(String.valueOf(commentData.getThumbsUpNumber()));;
            replyViewHolder.thumbsDownNumber.setText(String.valueOf(commentData.getThumbsDownNumber()));;

            // 프로필 사진을 눌렀을 경우
            replyViewHolder.profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    if (context instanceof AppCompatActivity) {
                        CustomDialog customDialog = CustomDialog.newInstance(commentData.getNickname(), commentData.getProfileImageId());
                        customDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "customDialog");
                    }
                }
            });

            // 대댓글 좋아요 버튼을 눌렀을 경우
            replyViewHolder.imageThumbsUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = replyViewHolder.getAdapterPosition();
                    CommentData currentData = commentDataList.get(adapterPosition);

                    boolean isClicked = currentData.isThumbsUpClicked();
                    currentData.setThumbsUpClicked(!isClicked);

                    if (!isClicked) {
                        replyViewHolder.imageThumbsUp.setColorFilter(Color.parseColor("#94DEF7"));
                        commentData.setThumbsUpNumber(commentData.getThumbsUpNumber() + 1);
                    } else {
                        replyViewHolder.imageThumbsUp.setColorFilter(Color.parseColor("#A4A4A4"));
                        commentData.setThumbsUpNumber(commentData.getThumbsUpNumber() - 1);
                    }
                    replyViewHolder.thumbsUpNumber.setText(String.valueOf(currentData.getThumbsUpNumber()));
                }
            });

            // 대댓글 싫어요 버튼을 눌렀을 경우
            replyViewHolder.imageThumbsDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = replyViewHolder.getAdapterPosition();
                    CommentData currentData = commentDataList.get(adapterPosition);

                    boolean isClicked = currentData.isThumbsDownClicked();
                    currentData.setThumbsDownClicked(!isClicked);

                    if (!isClicked) {
                        replyViewHolder.imageThumbsDown.setColorFilter(Color.parseColor("#94DEF7"));
                        commentData.setThumbsDownNumber(commentData.getThumbsDownNumber() + 1);
                    } else {
                        replyViewHolder.imageThumbsDown.setColorFilter(Color.parseColor("#A4A4A4"));
                        commentData.setThumbsDownNumber(commentData.getThumbsDownNumber() - 1);
                    }
                    replyViewHolder.thumbsDownNumber.setText(String.valueOf(currentData.getThumbsDownNumber()));
                }
            });

            // 대댓글 번역 아이콘을 눌렀을 경우
            replyViewHolder.imageTranslation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommentData data = commentDataList.get(holder.getAdapterPosition());

                    data.setTranslated(!data.isTranslated());

                    if (data.isTranslated()) {
                        replyViewHolder.content.setText(data.getTranslateContent());
                        replyViewHolder.imageTranslation.setColorFilter(Color.parseColor("#94DEF7"));
                    } else {
                        replyViewHolder.content.setText(data.getContent());
                        replyViewHolder.imageTranslation.setColorFilter(Color.parseColor("#A4A4A4"));
                    }
                }
            });

            // reply 이미지를 눌렀을 경우
            replyViewHolder.imageRecomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    if (context instanceof FeedDetailActivity) {
                        FeedDetailActivity activity = (FeedDetailActivity) context;
                        activity.enableReplyMode(commentData.getReplyToCommentId(), commentData.getNickname());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return commentDataList.size();
    }
}
