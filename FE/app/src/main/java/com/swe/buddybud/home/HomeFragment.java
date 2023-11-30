package com.swe.buddybud.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.swe.buddybud.R;
import com.swe.buddybud.board.BoardApiData;
import com.swe.buddybud.board.BoardApiService;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.user.LoginData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private FeedAdapter adapter;
    private List<FeedData> dataList;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        EditText editTextSearch = rootView.findViewById(R.id.editTextSearch);
        ImageView searchButton = rootView.findViewById(R.id.searchButton);
        ImageView imageWritePost = rootView.findViewById(R.id.imageWritePost);

        recyclerView = rootView.findViewById(R.id.recycleView);

        // 1. 홈 화면 진입시 Api 요청
        dataList = new ArrayList<>(); // FeedData 리스트 초기화
        adapter = new FeedAdapter(new ArrayList<>()); // 어댑터 초기화
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDecoration(20));

        loadBoardData("SNS", LoginData.getLoginUserNo()); // 초기 데이터 로드

        // 검색 버튼 리스너 설정
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = editTextSearch.getText().toString();
                List<FeedData> filteredFeeds = DataManager.getInstance().filterFeeds(searchText);
                adapter.setDataList(filteredFeeds);
                adapter.notifyDataSetChanged();

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
            }
        });

        // 글쓰기 버튼 리스너 설정
        imageWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WritePostActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // 백엔드에서 최신 데이터를 다시 로드
        loadBoardData("SNS", LoginData.getLoginUserNo());
    }

    private void loadBoardData(String board_type, Integer user_no) {
        String boardType = board_type;
        Integer userNo = user_no;

        BoardApiService boardApiServices = RetrofitClient.getService(BoardApiService.class);
        Call<List<Map<String, String>>> call = boardApiServices.getBoardsList(boardType, userNo);

        call.enqueue(new Callback<List<Map<String, String>>>() {
            @Override
            public void onResponse(Call<List<Map<String, String>>> call, Response<List<Map<String, String>>> response) {
                if (response.isSuccessful()) {
                    List<FeedData> newDataList = new ArrayList<>();
                    List<Map<String, String>> boardList = response.body();

                    dataList.clear();
                    for (Map<String, String> boardItem : boardList) {
                        // 각 게시물 정보를 FeedData 객체로 변환
                        FeedData feedData = new FeedData(
                                Integer.parseInt(boardItem.get("post_no")),
                                R.drawable.logo_profile, // 프로필 이미지 임시 값
                                "temp_nickname", // 닉네임 임시 값
                                boardItem.get("created_at"),
                                boardItem.get("title"),
                                boardItem.get("content"),
                                Integer.parseInt(boardItem.get("like_num")),
                                Integer.parseInt(boardItem.get("comment_num")),
                                null  // 추가 필요한 필드
                        );
                        dataList.add(feedData); // 리스트에 추가
                    }

                    // 데이터 리스트 업데이트 및 어댑터 갱신
                    dataList.addAll(newDataList);
                    adapter.setDataList(dataList);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("error", "server error");
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, String>>> call, Throwable t) {
                Log.d("error", "network error: " + t.getMessage());
            }
        });
    }
}