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
import android.widget.Button;
import android.widget.ImageView;

import com.swe.buddybud.R;
import com.swe.buddybud.account.PostScrapAdapter;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    private RecyclerView postsRecyclerView, scrapsRecyclerView;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        postsRecyclerView = view.findViewById(R.id.posts_recycler_view);
        scrapsRecyclerView = view.findViewById(R.id.scraps_recycler_view);

        // toggle button
        Button myPostsButton = view.findViewById(R.id.my_posts_button);
        Button myScrapsButton = view.findViewById(R.id.my_scraps_button);

        // recycle view 
        setupRecyclerView(postsRecyclerView, loadPosts());
        setupRecyclerView(scrapsRecyclerView, loadScraps()); // Initially empty

        // 유저 프로파일 이미지 설정
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

    private List<PostScrapData> loadPosts() {
        List<PostScrapData> posts = new ArrayList<>();

        String userid = com.swe.buddybud.user.LoginData.getLoginUserId();

        // Add test data with the userid from LoginData
//        posts.add(new PostScrapData(userid, "2023-11-01 10:00", "Post Title 1", "Content of the first post"));
//        posts.add(new PostScrapData(userid, "2023-11-01 11:30", "Post Title 2", "Content of the second post"));
//        posts.add(new PostScrapData(userid, "2023-11-01 12:45", "Post Title 3", "Content of the third post"));
        return posts;
    }

    private List<PostScrapData> loadScraps() {
        List<PostScrapData> scraps = new ArrayList<>();
        // Add test data
//        scraps.add(new PostScrapData("User1", "2023-11-01 10:00", "Scrap Title 1", "Content of the first scrap"));
//        scraps.add(new PostScrapData("User2", "2023-11-01 11:30", "Scrap Title 2", "Content of the second scrap"));
//        scraps.add(new PostScrapData("User3", "2023-11-01 12:45", "Scrap Title 3", "Content of the third scrap"));
        return scraps;
    }
}
