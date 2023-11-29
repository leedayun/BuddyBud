package com.swe.buddybud.willow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import com.swe.buddybud.R;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.user.LoginData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentWillowActivity extends AppCompatActivity {
    private RecyclerView sentWillowRcView;
    private SentWillow_Adapter sentWillowAdapter;
    ArrayList<SentWillowData> sentWillowItems = new ArrayList<>();
    private WillowApiService willowApiService = RetrofitClient.getService(WillowApiService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_willow);

        sentWillowRcView = findViewById(R.id.sent_willow_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        sentWillowRcView.setLayoutManager(layoutManager);
        sentWillowAdapter = new SentWillow_Adapter(sentWillowItems);
        sentWillowRcView.setAdapter(sentWillowAdapter);
        getData();
    }

    private ArrayList<SentWillowData> getData(){
        sentWillowItems.clear();

        //TODO: sentWillow -> sentWillowItems
        Call<List<WillowApiData>> call = willowApiService.sentWillow(LoginData.getLoginUserNo());
        call.enqueue(new Callback<List<WillowApiData>>() {
            @Override
            public void onResponse(Call<List<WillowApiData>> call, Response<List<WillowApiData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<WillowApiData> userResponse = response.body();
                    for(WillowApiData res : userResponse){
                        Resources resources = getResources();
                        int resourceId = R.drawable.profile;
                        if(res.getSender_no()!=LoginData.getLoginUserNo()) {
                            int foundProfileImg = resources.getIdentifier(res.getReceiver_id().toLowerCase(), "drawable", getPackageName());
                            if (foundProfileImg > 0) resourceId = foundProfileImg;
                        }
                        sentWillowItems.add(new SentWillowData(res.getReceiver_id(),resourceId));
                    }
                    sentWillowAdapter.notifyDataSetChanged();
                    return;
                }
                else {
                    Toast.makeText(getApplicationContext(),"sentWillow Network Error", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            @Override
            public void onFailure(Call<List<WillowApiData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"sentWillow Network Error", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        /*
        SentWillowData spideytom = new SentWillowData("mililili",R.drawable.mililili);
        SentWillowData superson = new SentWillowData("superSon",R.drawable.superson);
        sentWillowItems.add(spideytom);
        sentWillowItems.add(superson);
        */
        return sentWillowItems;
    }
}