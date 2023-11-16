package com.swe.buddybud;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;


public class AccountFragment extends Fragment {

    private ScrollView postsScrollView; // 게시물 ScrollView
    private ScrollView scrapsScrollView; // 스크랩 ScrollView
    private Button myPostsButton;
    private Button myScrapsButton;
    private ImageView profileImage;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Handle fragment arguments if any
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        postsScrollView = view.findViewById(R.id.posts_scroll_view);
        scrapsScrollView = view.findViewById(R.id.scraps_scroll_view);
        myPostsButton = view.findViewById(R.id.my_posts_button);
        myScrapsButton = view.findViewById(R.id.my_scraps_button);

        // Set up the listeners for the buttons
        myPostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPosts();
            }
        });

        myScrapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScraps();
            }
        });

        profileImage = view.findViewById(R.id.profile_image);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountEdit();
            }
        });

        return view;
    }
    private void showPosts() {
        postsScrollView.setVisibility(View.VISIBLE); // 게시물 ScrollView를 보이게 설정
        scrapsScrollView.setVisibility(View.GONE); // 스크랩 ScrollView를 숨김
    }

    private void showScraps() {
        postsScrollView.setVisibility(View.GONE); // 게시물 ScrollView를 숨김
        scrapsScrollView.setVisibility(View.VISIBLE); // 스크랩 ScrollView를 보이게 설정
    }

    private void openAccountEdit() {
        // 계정 정보를 수정할 액티비티로 이동
        Intent intent = new Intent(getActivity(), AccountEditActivity.class);
        startActivity(intent);
    }
}
