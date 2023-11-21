package com.swe.buddybud.willow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.swe.buddybud.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WillowChatActivity extends AppCompatActivity {
    private String userID = null;
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private ArrayList<ChatData> data = new ArrayList<>();
    private RecyclerView chatRecyclerView;
    private RecyclerView imageRecyclerView;
    private WillowChat_Adapter chatAdapter;
    private ImageButton backBtn;
    private ImageButton addImgBtn;
    private ImageButton sendChatBtn;
    private EditText chatEditText;
    private ArrayList<Uri> selectedImageUris = new ArrayList<>();
    private ImageUploadAdapter imageUploadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_willow_chat);

        userID = getIntent().getStringExtra("USER_ID");
        if(userID == null) userID = "user1";

        chatRecyclerView = findViewById(R.id.chat_recyclerView);
        imageRecyclerView = findViewById(R.id.chat_image_recycler_view);

        chatAdapter = new WillowChat_Adapter(getData(), userID);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        chatRecyclerView.setAdapter(chatAdapter);

        imageUploadAdapter = new ImageUploadAdapter(selectedImageUris);
        imageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imageRecyclerView.setAdapter(imageUploadAdapter);

        backBtn = findViewById(R.id.chat_back_btn);
        addImgBtn = findViewById(R.id.add_image_btn);
        sendChatBtn = findViewById(R.id.send_chat_btn);
        chatEditText = findViewById(R.id.chat_edittext);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {finish();}
        });

        addImgBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });

        sendChatBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(selectedImageUris.size() > 1) {
                    for(Uri imageUri : selectedImageUris){
                        data.add(new ChatData(LocalTime.now(), "","user1",R.drawable.profile, imageUri, ""));
                    }
                    selectedImageUris.clear();
                    imageUploadAdapter.notifyDataSetChanged();
                }
                if(chatEditText.getText().length()<1) return;
                data.add(new ChatData(LocalTime.now(), chatEditText.getText().toString(),"user1",R.drawable.profile, null, ""));
                chatEditText.setText("");
                chatAdapter.notifyItemInserted(data.size() - 1);
                chatRecyclerView.scrollToPosition(data.size() - 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri imageUri = clipData.getItemAt(i).getUri();

                        try {
                            // 내부 저장소에 파일 복사
                            File file = createImageFile();
                            copyUriToFile(imageUri, file);

                            // 내부 저장소 URI를 사용
                            Uri internalUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
                            selectedImageUris.add(internalUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (data.getData() != null) {
                    Uri imageUri = data.getData();

                    try {
                        // 내부 저장소에 파일 복사
                        File file = createImageFile();
                        copyUriToFile(imageUri, file);

                        // 내부 저장소 URI를 사용
                        Uri internalUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
                        selectedImageUris.add(internalUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                imageUploadAdapter.notifyDataSetChanged();
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

    private void copyUriToFile(Uri uri, File destFile) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = getContentResolver().openInputStream(uri);
            os = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    private ArrayList<ChatData> getData(){
        data = new ArrayList<>();
        data.add(new ChatData(LocalTime.of(7, 25), "안녕하세요 전 빈지노에요","realisshoman",R.drawable.realisshoman, null, "Hello, I'm Beenzino."));
        data.add(new ChatData(LocalTime.of(9, 30), "Thank you for coming to the festival. I’m enjoying the song haha.","user1",R.drawable.profile, null, "축제 와주셔서 감사합니다 노래 잘 듣고 있습니다 ㅎㅎ."));
        data.add(new ChatData(LocalTime.of(13, 01), "안녕하세요 전 빈지노에요","realisshoman",R.drawable.realisshoman, null, "Hello, I'm Beenzino."));
        data.add(new ChatData(LocalTime.of(15, 22), "Thank you for accepting Willow.","user1",R.drawable.profile, null, "윌로우 받아주셔서 감사합니다"));
        data.add(new ChatData(LocalTime.of(23, 11),
                "살바도르 달리, 반고흐 같이 Picasso in my body. Man I'm freakin artist \n"+
                        "달리 반 피카소를 보며 난 자랐어 \n"+
                        "나도 물감을 짰고 난 여기까지 왔어 And you can tell me nothing",
                "realisshoman", R.drawable.realisshoman, null,
                "Like Salvador Dali and Van Gogh, Picasso in my body. Man I'm freakin artist\n" +
                "I grew up watching Dali and Picasso\n" +
                "I also applied paint and I came this far And you can tell me nothing"));
        return data;
    }
}