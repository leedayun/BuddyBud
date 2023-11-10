package com.swe.buddybud;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WillowFragment extends Fragment implements View.OnClickListener {
    public TextView seeAllText;
    public RecyclerView incomingWillowRcView;
    public RecyclerView myWillowsRcView;
    public RecyclerView.Adapter incomingWillowAdapter;
    public RecyclerView.Adapter myWillowsAdapter;
    public ArrayList<IncomingWillowData> incomingWillows = new ArrayList<>();
    public ArrayList<IncomingWillowData> incomingWillowItems = new ArrayList<>();
    public ArrayList<MyWillowsData> myWillowsItems = new ArrayList<>();

    protected RecyclerView.LayoutManager iclayoutManager;
    protected RecyclerView.LayoutManager mwlayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        incomingWillows = new ArrayList<>();
        IncomingWillowData incominguser3 = new IncomingWillowData("testuser3","Software","male", R.drawable.profile);
        IncomingWillowData incominguser4 = new IncomingWillowData("testuser4","Chemistry","female",  R.drawable.profile);
        incomingWillows.add(incominguser3);
        incomingWillows.add(incominguser4);
        Log.d("incomingWillows",incomingWillows.toString());

        if(incomingWillows.size()>3) {
            incomingWillowItems = new ArrayList<IncomingWillowData>();
            incomingWillowItems.add(incomingWillows.get(0));
        } else {
            incomingWillowItems = incomingWillows;
        }

        myWillowsItems = new ArrayList<>();
        MyWillowsData mu = new MyWillowsData("testuser2",LocalDateTime.now().minusMinutes(5),"ok", R.drawable.profile);
        myWillowsItems.add(mu);
        Log.d("MyWillows1",myWillowsItems.toString());
        mu = new MyWillowsData("testuser1", LocalDateTime.now().minusHours(2),"hi", R.drawable.profile);
        myWillowsItems.add(mu);
        Log.d("MyWillows2",myWillowsItems.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_willow, container,false);
        incomingWillowRcView = view.findViewById(R.id.incoming_willow_view);
        myWillowsRcView = view.findViewById(R.id.my_willows_view);
        //incomingWillowRcView.setHasFixedSize(false);
        //myWillowsRcView.setHasFixedSize(false);
        seeAllText = view.findViewById(R.id.seeall_text);
        seeAllText.setOnClickListener(this);

        iclayoutManager = new LinearLayoutManager(getActivity());
        mwlayoutManager = new LinearLayoutManager(getActivity());

        incomingWillowAdapter = new IncomingWillow_Adapter(incomingWillowItems);
        myWillowsAdapter = new MyWillows_Adapter(myWillowsItems);

        incomingWillowRcView.setLayoutManager(iclayoutManager);
        myWillowsRcView.setLayoutManager(mwlayoutManager);

        incomingWillowRcView.setAdapter(incomingWillowAdapter);
        myWillowsRcView.setAdapter(myWillowsAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(incomingWillows.size()>1)  seeAllText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.seeall_text:{
                incomingWillowItems = incomingWillows;
                view.setVisibility(View.INVISIBLE);
                incomingWillowAdapter.notifyDataSetChanged();
                break;
            }
        }
    }
}