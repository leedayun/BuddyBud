package com.swe.buddybud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.time.LocalTime;
import java.util.ArrayList;

public class WillowChatActivity extends AppCompatActivity {
    private String userID = null;
    ArrayList<ChatData> data = new ArrayList<>();
    RecyclerView chatRecyclerView;
    WillowChat_Adapter chatAdapter;
    ImageButton backBtn;
    ImageButton addImgBtn;
    ImageButton sendChatBtn;
    EditText chatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_willow_chat);

        userID = getIntent().getStringExtra("USER_ID");
        if(userID == null) userID = "user1";

        chatRecyclerView = findViewById(R.id.chat_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        chatRecyclerView.setLayoutManager(layoutManager);
        chatAdapter = new WillowChat_Adapter(getData(), userID);
        chatRecyclerView.setAdapter(chatAdapter);

        backBtn = findViewById(R.id.chat_back_btn);
        addImgBtn = findViewById(R.id.add_image_btn);
        sendChatBtn = findViewById(R.id.send_chat_btn);
        chatEditText = findViewById(R.id.chat_edittext);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {finish();}
        });

        sendChatBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                data.add(new ChatData(LocalTime.now(), chatEditText.getText().toString(),"user1",R.drawable.profile));
                chatEditText.setText("");
                chatAdapter.notifyItemInserted(data.size() - 1);
                chatRecyclerView.scrollToPosition(data.size() - 1);
            }
        });

    }

    private ArrayList<ChatData> getData(){
        data = new ArrayList<>();
        data.add(new ChatData(LocalTime.of(7, 25), "안녕하세요 전 빈지노에요","realisshoman",R.drawable.profile));
        data.add(new ChatData(LocalTime.of(9, 30), "축제 와주셔서 감사합니다 노래 잘 듣고 있습니다 ㅎㅎ","user1",R.drawable.profile));
        data.add(new ChatData(LocalTime.of(13, 01), "안녕하세요 전 빈지노에요","realisshoman",R.drawable.profile));
        data.add(new ChatData(LocalTime.of(15, 22), "윌로우 받아주셔서 감사합니다","user1",R.drawable.profile));
        data.add(new ChatData(LocalTime.of(23, 11), "살바도르 달리, 반고흐 같이 Picasso in my body. Man I'm freakin artist \n달리 반 피카소를 보며 난 자랐어 \n나도 물감을 짰고 난 여기까지 왔어 And you can tell me nothing","realisshoman",R.drawable.profile));
        return data;
    }
}