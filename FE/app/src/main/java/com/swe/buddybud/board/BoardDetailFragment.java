package com.swe.buddybud.board;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.swe.buddybud.R;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.home.CommentAdapter;
import com.swe.buddybud.home.CommentData;
import com.swe.buddybud.home.FeedDetailActivity;
import com.swe.buddybud.home.RecycleViewDecoration;
import com.swe.buddybud.user.LoginData;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetailFragment extends Fragment {

    public BoardDetailFragment () {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_board_detail, container, false);

        int feedId = -1;
        // 번들에서 feed_id 얻기
        Bundle bundle = getArguments();
        if (bundle != null) {
            feedId = bundle.getInt("feed_id", -1); // 기본값으로 -1을 사용
        }

        BoardApiService boardApiServices = RetrofitClient.getService(BoardApiService.class);
        Call<JsonObject> call = boardApiServices.getBoardDetails("Notice", LoginData.getLoginUserNo(), feedId);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject boardDetails = response.body();

                    Log.d("test", boardDetails.get("title").getAsString());
                    CircleImageView profileImageView = rootView.findViewById(R.id.feedProfileImage);
                    TextView nicknameTextView = rootView.findViewById(R.id.feedNickname);
                    TextView dateTextView = rootView.findViewById(R.id.feedDate);
                    TextView titleTextView = rootView.findViewById(R.id.feedTitle);
                    TextView contentTextView = rootView.findViewById(R.id.feedContent);
                    ImageView thumbsUpImageView = rootView.findViewById(R.id.imageThumbsUp);
                    TextView thumbsUpNumberTextView = rootView.findViewById(R.id.feedThumbsUpNumber);
                    ImageView scrapImageView = rootView.findViewById(R.id.imageScrap);
                    TextView scrapNumberTextView = rootView.findViewById(R.id.feedScrapNumber);
                    ImageView translationImageView = rootView.findViewById(R.id.imageTranslation);

                    //임시
                    profileImageView.setImageResource(R.drawable.logo_profile);
                    nicknameTextView.setText("temp_nickname");

                    dateTextView.setText(boardDetails.get("created_at").getAsString());
                    titleTextView.setText(boardDetails.get("title").getAsString());
                    contentTextView.setText(boardDetails.get("content").getAsString());
                    thumbsUpNumberTextView.setText(String.valueOf(boardDetails.get("like_num").getAsInt()));
                    scrapNumberTextView.setText(String.valueOf(boardDetails.get("scrap_num").getAsInt()));

                } else {
                    Log.d("FeedDetail", "Server error");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("FeedDetail", "Network error: " + t.getMessage());
            }
        });


        return rootView;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        ImageButton back_1_Button = view.findViewById(R.id.back_1_Button);
//        back_1_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new BoardFragment();
//
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.board_detail_container, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//    }
}