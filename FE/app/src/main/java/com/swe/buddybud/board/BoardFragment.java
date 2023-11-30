package com.swe.buddybud.board;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.swe.buddybud.R;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.home.DataManager;
import com.swe.buddybud.home.FeedAdapter;
import com.swe.buddybud.home.FeedData;
import com.swe.buddybud.home.RecycleViewDecoration;
import com.swe.buddybud.user.LoginData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardFragment extends Fragment {
    private RecyclerView recyclerView;
    private BoardAdapter adapter;
    private List<BoardData> dataList;

    public BoardFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_board, container, false);

        EditText editTextSearch = rootView.findViewById(R.id.editTextSearch);
        ImageView searchButton = rootView.findViewById(R.id.searchButton);
        ImageView filterButton = rootView.findViewById(R.id.filterButton);

        recyclerView = rootView.findViewById(R.id.recycleView);

        // 1. 보드 화면 진입시 Api 요청
        dataList = new ArrayList<>(); // FeedData 리스트 초기화
        adapter = new BoardAdapter(new ArrayList<>()); // 어댑터 초기화
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDecoration(20));

        loadBoardData("Notice", LoginData.getLoginUserNo()); // 초기 데이터 로드

        // 검색 버튼 리스너 설정
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String searchText = editTextSearch.getText().toString();
//                List<BoardData> filteredBoards = DataManager.getInstance().filterFeeds(searchText);
//                adapter.setDataList(filteredFeeds);
//                adapter.notifyDataSetChanged();
//
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
//            }
//        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // 백엔드에서 최신 데이터를 다시 로드
        loadBoardData("Notice", LoginData.getLoginUserNo());
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
                    List<BoardData> newDataList = new ArrayList<>();
                    List<Map<String, String>> boardList = response.body();

                    dataList.clear();
                    for (Map<String, String> boardItem : boardList) {
                        BoardData boardData = new BoardData(
                                Integer.parseInt(boardItem.get("post_no")),
                                R.drawable.logo_profile, // 프로필 이미지 임시 값
                                "temp_nickname", // 닉네임 임시 값
                                boardItem.get("created_at"),
                                boardItem.get("title"),
                                boardItem.get("content"),
                                Integer.parseInt(boardItem.get("like_num")),
                                Integer.parseInt(boardItem.get("comment_num"))
                        );
                        dataList.add(boardData);
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


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        ImageView frame_1 = view.findViewById(R.id.frame_1_Shadow);
//        frame_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new BoardDetailFragment();
//
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.board_container, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        ImageView filterButton = view.findViewById(R.id.filterButton);
//        filterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new BoardFilterFragment();
//
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.board_container, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//    }
}