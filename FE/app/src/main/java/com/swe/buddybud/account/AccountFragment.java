package com.swe.buddybud.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.swe.buddybud.R;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.user.LoginData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    private RecyclerView postsRecyclerView, scrapsRecyclerView;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        String userid = LoginData.getLoginUserId();
        TextView user_id_textview = view.findViewById(R.id.user_name);
        user_id_textview.setText(userid);

        postsRecyclerView = view.findViewById(R.id.posts_recycler_view);
        scrapsRecyclerView = view.findViewById(R.id.scraps_recycler_view);

        // toggle button
        ImageButton myPostsButton = view.findViewById(R.id.my_posts_button);
        ImageButton myScrapsButton = view.findViewById(R.id.my_scraps_button);

        // load data from server api
        loadPosts(userid);
        loadScraps(userid);

        // set user's profile image
        ImageView profileImageView = view.findViewById(R.id.profile_image);

        // 유저 프로파일 이미지 클릭하면 계정 설정 창으로 넘어가게 만드는 클릭 리스너
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountEditActivity.class);
                startActivity(intent);
            }
        });

        // 내 포스트 보이게 만드는 포스트 버튼 클릭 리스너
        myPostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsRecyclerView.setVisibility(View.VISIBLE);
                scrapsRecyclerView.setVisibility(View.GONE);
            }
        });

        // 내 스크랩 보이게 만드는 스크랩 버튼 클릭 리스너
        myScrapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsRecyclerView.setVisibility(View.GONE);
                scrapsRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<PostScrapData> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PostScrapAdapter adapter = new PostScrapAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private void loadPosts(String userid) {
        AccountApiService accountApiService = RetrofitClient.getService(AccountApiService.class);

        Call<AccountApiData> call = accountApiService.getUserPosts(userid);
        call.enqueue(new Callback<AccountApiData>() {
            @Override
            public void onResponse(Call<AccountApiData> call, Response<AccountApiData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PostData> posts = response.body().getPosts();
                    if (posts != null) {
                        List<PostScrapData> postScrapDataList = convertPostDataToPostScrapData(posts, userid);
                        setupRecyclerView(postsRecyclerView, postScrapDataList);
                    }
                } else {
                    // Handle failure
                    Log.d("API Response", "no response");
                    if (getActivity() != null) {
                        Toast.makeText(getActivity(), "No Posts", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountApiData> call, Throwable t) {
                // Handle network error
                Log.d("API Failure", t.getMessage());
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void loadScraps(String userid) {
        AccountApiService accountApiService = RetrofitClient.getService(AccountApiService.class);

        Call<AccountApiData> call = accountApiService.getUserScraps(userid);
        call.enqueue(new Callback<AccountApiData>() {
            @Override
            public void onResponse(Call<AccountApiData> call, Response<AccountApiData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ScrapData> scraps = response.body().getScraps();
                    if (scraps != null) {
                        List<PostScrapData> scrapDataList = convertScrapDataToPostScrapData(scraps, userid);
                        setupRecyclerView(scrapsRecyclerView, scrapDataList);
                    }
                } else {
                    Log.d("API Response", "no response");
                    if (getActivity() != null) {
                        Toast.makeText(getActivity(), "No Scraps", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountApiData> call, Throwable t) {
                Log.d("API Failure", t.getMessage());
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private List<PostScrapData> convertPostDataToPostScrapData(List<PostData> postDataList, String userid) {
        List<PostScrapData> postScrapDataList = new ArrayList<>();

        for (PostData postData : postDataList) {
            int postId = Integer.parseInt(postData.getPostNo()); // Assuming post_no can be parsed as an integer.
            int thumbsUpNumber = Integer.parseInt(postData.getLikeNum()); // Assuming like_num is a number.

            // Create a PostScrapData object using the values from postData.
            PostScrapData postScrapData = new PostScrapData(
                    postId, // id
                    0, // profileImageId, assuming you don't have this info in the API response
                    userid, // userid, assuming you will get it from somewhere else or set a default value
                    postData.getCreatedAt(), // date
                    postData.getTitle(), // title
                    postData.getContent(), // content
                    thumbsUpNumber, // thumbsUpNumber
                    0, // commentNumber, assuming you don't have this info in the API response
                    null // imageUris, assuming you don't have this info in the API response
            );

            postScrapDataList.add(postScrapData);
        }

        return postScrapDataList;
    }

    private List<PostScrapData> convertScrapDataToPostScrapData(List<ScrapData> scrapDataList, String userid) {
        List<PostScrapData> postScrapDataList = new ArrayList<>();

        for (ScrapData scrapData : scrapDataList) {
            int scrapId = Integer.parseInt(scrapData.getScrapNum()); // Convert scrap number to int.
            int thumbsUpNumber = Integer.parseInt(scrapData.getLikeNum()); // Convert like number to int.

            PostScrapData postScrapData = new PostScrapData(
                    scrapId, // id
                    0, // profileImageId, assuming you don't have this info in the API response
                    userid, // userid, assuming you will get it from somewhere else or set a default value
                    scrapData.getCreatedAt(), // date
                    scrapData.getTitle(), // title
                    scrapData.getContent(), // content
                    thumbsUpNumber, // thumbsUpNumber
                    0, // commentNumber, assuming you don't have this info in the API response
                    null // imageUris, assuming you don't have this info in the API response
            );

            postScrapDataList.add(postScrapData);
        }

        return postScrapDataList;
    }
}
