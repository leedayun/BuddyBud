package com.swe.buddybud.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.swe.buddybud.R;

import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private FeedAdapter adapter;

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


        imageWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WritePostActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = rootView.findViewById(R.id.recycleView);

        List<FeedData> dataList = DataManager.getInstance().getFeedList();

        adapter = new FeedAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new RecycleViewDecoration(20));

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
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // DataManager에서 최신 데이터를 가져와 어댑터를 갱신
        List<FeedData> updatedDataList = DataManager.getInstance().getFeedList();
        adapter.setDataList(updatedDataList);
        adapter.notifyDataSetChanged();
    }
}