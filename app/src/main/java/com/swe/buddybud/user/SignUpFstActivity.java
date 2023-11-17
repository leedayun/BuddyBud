package com.swe.buddybud.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.swe.buddybud.R;

public class SignUpFstActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText editTextSchool;
    private EditText editTextStudentIdCard;
    private Button btnNext;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_fst);

        EditText editTextSchool = findViewById(R.id.editTextSchool);
        EditText editTextStudentIdCard = findViewById(R.id.editTextStudentIdCard);
        Button btnNext = findViewById(R.id.buttonNext);

        // 학교 검색
        editTextSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        
        // 학생증 업로드
        editTextStudentIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        // Next 버튼 클릭 시 : SignUpSndActivity
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpSndActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();

            // 선택한 이미지 파일 이름을 EditText에 표시
            EditText editTextStudentIdCard = findViewById(R.id.editTextStudentIdCard);
            String imageName = getRealPathFromURI(selectedImageUri);
            String[] imageNameArray = imageName.split("/");
            editTextStudentIdCard.setText(imageNameArray[imageNameArray.length - 1]);
        }
    }

    // URI에서 실제 파일 경로 가져오기
    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null){
            return null;
        }

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();

        return result;
    }
}