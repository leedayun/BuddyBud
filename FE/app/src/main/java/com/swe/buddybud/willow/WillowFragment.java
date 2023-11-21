package com.swe.buddybud.willow;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;

import com.swe.buddybud.R;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class WillowFragment extends Fragment implements View.OnClickListener, WillowManageInterface {
    public TextView seeAllText;
    public ImageButton searchBtn;
    public ImageButton sentWillowBtn;
    public RecyclerView incomingWillowRcView;
    public RecyclerView myWillowsRcView;
    public IncomingWillow_Adapter incomingWillowAdapter;
    public MyWillows_Adapter myWillowsAdapter;
    public ArrayList<IncomingWillowData> incomingWillows = new ArrayList<>();
    public ArrayList<IncomingWillowData> incomingWillowItems = new ArrayList<>();
    public ArrayList<MyWillowsData> myWillowsItems = new ArrayList<>();

    protected RecyclerView.LayoutManager iclayoutManager;
    protected RecyclerView.LayoutManager mwlayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        incomingWillows = new ArrayList<>();
        IncomingWillowData woosik = new IncomingWillowData("Woosik12","software","male", R.drawable.woosik);
        IncomingWillowData marvelyvly = new IncomingWillowData("marvelyvly","Chemistry","female",  R.drawable.marvelyvly);
        incomingWillows.add(woosik);
        incomingWillows.add(marvelyvly);

        if(incomingWillows.size()>1) {
            incomingWillowItems = new ArrayList<IncomingWillowData>();
            incomingWillowItems.add(incomingWillows.get(0));
        } else {
            incomingWillowItems = incomingWillows;
        }

        myWillowsItems = new ArrayList<>();
        MyWillowsData realisshoman = new MyWillowsData("realisshoman",LocalDateTime.of(2023, 1, 23, 07, 18),"살바도르 달리, 반고흐 같이 Picasso in my body. Man I'm freakin artist", R.drawable.realisshoman);
        MyWillowsData nakedbibi = new MyWillowsData("nakedbibi", LocalDateTime.of(2022, 12, 25, 11, 24),"well, thank you have a nice day", R.drawable.nakedbibi);
        MyWillowsData calmdownman = new MyWillowsData("calmdownman", LocalDateTime.of(2022, 9, 3, 1, 11),"Wow! thank you for your support", R.drawable.calmdownman);
        myWillowsItems.add(realisshoman);
        myWillowsItems.add(nakedbibi);
        myWillowsItems.add(calmdownman);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_willow, container,false);
        incomingWillowRcView = view.findViewById(R.id.incoming_willow_view);
        myWillowsRcView = view.findViewById(R.id.my_willows_view);
        seeAllText = view.findViewById(R.id.seeall_text);
        sentWillowBtn = view.findViewById(R.id.sent_willow_btn);
        searchBtn = view.findViewById(R.id.search_btn);
        seeAllText.setOnClickListener(this);
        sentWillowBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);


        iclayoutManager = new LinearLayoutManager(getActivity());
        mwlayoutManager = new LinearLayoutManager(getActivity());

        incomingWillowAdapter = new IncomingWillow_Adapter(incomingWillowItems, this);
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
        Log.w("test","click " + view.getId());
        switch(view.getId()){
            case R.id.seeall_text:{
                incomingWillowItems = incomingWillows;
                incomingWillowAdapter.setmData(incomingWillowItems);
                view.setVisibility(View.INVISIBLE);
                incomingWillowRcView.invalidate();
                incomingWillowAdapter.notifyDataSetChanged();
                break;
            }
            case R.id.sent_willow_btn:{

                Context context = view.getContext();
                Intent intent = new Intent(context, SentWillowActivity.class);
                /*
                TODO: get global login data
                private SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                intent.putExtra("userID",preferences.getString("userid","user1"));
                 */
                intent.putExtra("userID","user1");
                context.startActivity(intent);
                break;
            }

            case R.id.search_btn:{
                break;
            }

        }
    }

    @Override
    public void onAddWillow(MyWillowsData newWillow) {
        myWillowsRcView.invalidate();
        int res = myWillowsAdapter.addData(newWillow);
        if(res>0) myWillowsAdapter.notifyItemInserted(res);
        incomingWillowRcView.invalidate();
        incomingWillowAdapter.notifyDataSetChanged();
    }
}