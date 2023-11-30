package com.swe.buddybud.board;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.swe.buddybud.R;
import com.swe.buddybud.home.DataManager;
import com.swe.buddybud.home.Translator;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private List<BoardData> dataList;

    public BoardAdapter(List<BoardData> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(List<BoardData> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new BoardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        BoardData data = dataList.get(position);

        holder.feedProfileImage.setImageResource(data.getProfileImageId());
        holder.feedNickname.setText(data.getNickname());
        holder.feedDate.setText(data.getDate());
        holder.feedTitle.setText(data.getTitle());
        holder.feedContent.setText(data.getContent());
        holder.feedThumbsUpNumber.setText(String.valueOf(data.getThumbsUpNumber()));
        holder.feedScrapNumber.setText(String.valueOf(data.getScrapNumber()));

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
                BoardData currentData = dataList.get(adapterPosition);

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

        // 스크랩 아이콘 색상 설정
        if (data.isScrapClicked()) {
            holder.imageScrap.setColorFilter(Color.parseColor("#94DEF7"));
        } else {
            holder.imageScrap.setColorFilter(Color.parseColor("#A4A4A4"));
        }

        // 스크랩 수 설정
        holder.feedScrapNumber.setText(String.valueOf(data.getScrapNumber()));

        // 제목과 내용 max line 설정
        holder.feedTitle.setMaxLines(1);
        holder.feedTitle.setEllipsize(TextUtils.TruncateAt.END);
        holder.feedContent.setMaxLines(4);
        holder.feedContent.setEllipsize(TextUtils.TruncateAt.END);

        // 번역 아이콘을 눌렀을 경우
        holder.imageTranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardData data = dataList.get(holder.getAdapterPosition());

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

        // 피드 아이템 클릭 리스너
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardDetailFragment boardDetailFragment = new BoardDetailFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("feed_id", data.getId());
                boardDetailFragment.setArguments(bundle);

                FragmentTransaction transaction = ((FragmentActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFrameLayout, boardDetailFragment);
                transaction.addToBackStack(null); // 백 스택에 추가
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        CircleImageView feedProfileImage;
        ImageView imageThumbsUp, imageScrap, imageTranslation;
        TextView feedNickname, feedDate, feedTitle, feedContent, feedThumbsUpNumber, feedScrapNumber;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            feedProfileImage = itemView.findViewById(R.id.feedProfileImage);
            imageThumbsUp = itemView.findViewById(R.id.imageThumbsUp);
            imageScrap = itemView.findViewById(R.id.imageScrap);
            imageTranslation = itemView.findViewById(R.id.imageTranslation);
            feedNickname = itemView.findViewById(R.id.feedNickname);
            feedDate = itemView.findViewById(R.id.feedDate);
            feedTitle = itemView.findViewById(R.id.feedTitle);
            feedContent = itemView.findViewById(R.id.feedContent);
            feedThumbsUpNumber = itemView.findViewById(R.id.feedThumbsUpNumber);
            feedScrapNumber = itemView.findViewById(R.id.feedScrapNumber);
        }
    }
}
