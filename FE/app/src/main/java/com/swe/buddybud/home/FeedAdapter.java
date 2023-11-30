package com.swe.buddybud.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swe.buddybud.R;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<FeedData> dataList;

    public FeedAdapter(List<FeedData> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(List<FeedData> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedData data = dataList.get(position);

        holder.feedProfileImage.setImageResource(data.getProfileImageId());
        holder.feedNickname.setText(data.getNickname());
        holder.feedDate.setText(data.getDate());
        holder.feedTitle.setText(data.getTitle());
        holder.feedContent.setText(data.getContent());
        holder.feedThumbsUpNumber.setText(String.valueOf(data.getThumbsUpNumber()));
        holder.feedCommentNumber.setText(String.valueOf(data.getCommentNumber()));

        // 좋아요 상태에 따른 버튼 색상 설정
        if (data.isThumbsUpClicked()) {
            holder.imageThumbsUp.setColorFilter(Color.parseColor("#94DEF7"));
        } else {
            holder.imageThumbsUp.setColorFilter(Color.parseColor("#A4A4A4"));
        }

        // 좋아요 버튼을 눌렀을 경우
        holder.imageThumbsUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                FeedData currentData = dataList.get(adapterPosition);

                boolean isClicked = currentData.isThumbsUpClicked();
                DataManager.getInstance().updateThumbsUp(currentData.getId(), !isClicked);

                if (!isClicked) {
                    holder.imageThumbsUp.setColorFilter(Color.parseColor("#94DEF7"));
                } else {
                    holder.imageThumbsUp.setColorFilter(Color.parseColor("#A4A4A4"));
                }

                holder.feedThumbsUpNumber.setText(String.valueOf(currentData.getThumbsUpNumber()));
            }
        });

        // 댓글 아이콘 색상 설정
        if (data.isCommentWritten()) {
            holder.imageComment.setColorFilter(Color.parseColor("#94DEF7"));
        } else {
            holder.imageComment.setColorFilter(Color.parseColor("#A4A4A4"));
        }

        // 댓글 수 설정
        holder.feedCommentNumber.setText(String.valueOf(data.getCommentNumber()));

        // 제목과 내용 max line 설정
        holder.feedTitle.setMaxLines(1);
        holder.feedTitle.setEllipsize(TextUtils.TruncateAt.END);
        holder.feedContent.setMaxLines(4);
        holder.feedContent.setEllipsize(TextUtils.TruncateAt.END);

        // 번역 아이콘을 눌렀을 경우
        holder.imageTranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedData data = dataList.get(holder.getAdapterPosition());

                data.setTranslated(!data.isTranslated());

                if (data.isTranslated()) {
                    Translator translator = new Translator();
                    translator.detectAndTranslate(data.getTitle(), new Translator.TranslationCallback() {
                        @Override
                        public void onTranslationDone(String translatedText) {
                            // 번역된 텍스트 처리
                            holder.feedTitle.setText(translatedText);
                        }
                        @Override
                        public void onTranslationError(Exception e) {
                            // 에러 처리
                            Log.e("Translation", "Error during translation", e);
                        }
                    });
                    translator.detectAndTranslate(data.getContent(), new Translator.TranslationCallback() {
                        @Override
                        public void onTranslationDone(String translatedText) {
                            // 번역된 텍스트 처리
                            holder.feedContent.setText(translatedText);
                        }
                        @Override
                        public void onTranslationError(Exception e) {
                            // 에러 처리
                            Log.e("Translation", "Error during translation", e);
                        }
                    });
                    //holder.feedTitle.setText(data.getTranslateTitle());
                    //holder.feedContent.setText(data.getTranslateContent());
                    holder.imageTranslation.setColorFilter(Color.parseColor("#94DEF7"));
                } else {
                    holder.feedTitle.setText(data.getTitle());
                    holder.feedContent.setText(data.getContent());
                    holder.imageTranslation.setColorFilter(Color.parseColor("#A4A4A4"));
                }
            }
        });

        // 프로필 사진을 눌렀을 경우
        holder.feedProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                if (context instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) context;
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    CustomDialog customDialog = CustomDialog.newInstance(data.getNickname(), data.getProfileImageId());
                    customDialog.show(fragmentManager, "customDialog");
                }
            }
        });

        // 피드 아이템 클릭 리스너
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(view.getContext(), FeedDetailActivity.class);
                detailIntent.putExtra("feed_id", data.getId());
                view.getContext().startActivity(detailIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        CircleImageView feedProfileImage;
        ImageView imageThumbsUp, imageComment, imageTranslation;
        TextView feedNickname, feedDate, feedTitle, feedContent, feedThumbsUpNumber, feedCommentNumber;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            feedProfileImage = itemView.findViewById(R.id.feedProfileImage);
            imageThumbsUp = itemView.findViewById(R.id.imageThumbsUp);
            imageComment = itemView.findViewById(R.id.imageComment);
            imageTranslation = itemView.findViewById(R.id.imageTranslation);
            feedNickname = itemView.findViewById(R.id.feedNickname);
            feedDate = itemView.findViewById(R.id.feedDate);
            feedTitle = itemView.findViewById(R.id.feedTitle);
            feedContent = itemView.findViewById(R.id.feedContent);
            feedThumbsUpNumber = itemView.findViewById(R.id.feedThumbsUpNumber);
            feedCommentNumber = itemView.findViewById(R.id.feedCommentNumber);
        }
    }
}
