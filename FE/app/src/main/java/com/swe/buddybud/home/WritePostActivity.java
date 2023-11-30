package com.swe.buddybud.home;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.swe.buddybud.board.BoardApiData;
import com.swe.buddybud.board.BoardApiService;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.willow.ImageUploadAdapter;
import com.swe.buddybud.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePostActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private AppCompatButton postButton;
    private ImageView cameraButton;
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private Uri selectedImageUri = null;
    private RecyclerView selectedImagesRecyclerView;
    private List<Uri> selectedImageUris = new ArrayList<>();
    private ImageUploadAdapter imageUploadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        // 뷰 바인딩
        titleEditText = findViewById(R.id.title);
        contentEditText = findViewById(R.id.content); // 레이아웃의 EditText 아이디 확인 필요
        postButton = findViewById(R.id.postButton);
        cameraButton = findViewById(R.id.imageCamera);

        // 뒤로가기 아이콘 클릭시
        ImageView iconBack = findViewById(R.id.iconBack);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // RecyclerView 설정
        selectedImagesRecyclerView = findViewById(R.id.selectedImagesRecyclerView);
        imageUploadAdapter = new ImageUploadAdapter(selectedImageUris);
        selectedImagesRecyclerView.setAdapter(imageUploadAdapter);
        selectedImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Camera 버튼 클릭 리스너
        // Camera 버튼 클릭 리스너
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });
        
        // Post 버튼 클릭 리스너
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                // 제목과 내용이 비어있지 않은 경우에만 API 호출
                if (!title.isEmpty() && !content.isEmpty()) {
                    insertBoard(title, content, 2); // user_no는 2로 설정
                } else {
                    // 제목 또는 내용이 비어 있을 경우 사용자에게 알림
                    Toast.makeText(WritePostActivity.this, "Please enter the title and content.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertBoard(String title, String content, int userNo) {
        Map<String, String> fields = new HashMap<>();
        fields.put("title", title);
        fields.put("content", content);
        fields.put("user_no", String.valueOf(userNo));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(fields));

        BoardApiService boardApiServices = RetrofitClient.getService(BoardApiService.class);
        Call<BoardApiData> call = boardApiServices.insertBoard(requestBody);

        call.enqueue(new Callback<BoardApiData>() {
            @Override
            public void onResponse(Call<BoardApiData> call, Response<BoardApiData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean result = response.body().getInsertBoardResult();
                    if (result) {
                        Toast.makeText(WritePostActivity.this, "Post successfully created.", Toast.LENGTH_SHORT).show();
                        finish(); // 작성 액티비티 종료
                    } else {
                        Toast.makeText(WritePostActivity.this, "Failed to create the post.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("WritePost", "Server error");
                }
            }

            @Override
            public void onFailure(Call<BoardApiData> call, Throwable t) {
                Log.d("WritePost", "Network error: " + t.getMessage());
            }
        });
    }

    // 아래는 게시글 이미지 관련 코드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
}