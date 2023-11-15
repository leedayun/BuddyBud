package com.swe.buddybud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.time.LocalTime;
import java.util.ArrayList;

public class SentWillowActivity extends AppCompatActivity {
    private String userID = null;
    private RecyclerView sentWillowRcView;
    private SentWillow_Adapter sentWillowAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_willow);
        userID = getIntent().getStringExtra("USER_ID");
        if(userID == null) userID = "user1";

        sentWillowRcView = findViewById(R.id.sent_willow_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        sentWillowRcView.setLayoutManager(layoutManager);
        sentWillowAdapter = new SentWillow_Adapter(getData(), userID);
        sentWillowRcView.setAdapter(sentWillowAdapter);
    }

    private ArrayList<SentWillowData> getData(){
        ArrayList<SentWillowData> sentWillowItems = new ArrayList<>();
        SentWillowData sentwillow1 = new SentWillowData("spideyTom",R.drawable.profile);
        SentWillowData sentwillow2 = new SentWillowData("superSon",R.drawable.profile);
        sentWillowItems.add(sentwillow1);
        sentWillowItems.add(sentwillow2);
        return sentWillowItems;
    }
}