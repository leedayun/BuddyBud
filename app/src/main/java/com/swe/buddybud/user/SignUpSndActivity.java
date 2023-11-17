package com.swe.buddybud.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.swe.buddybud.R;
import com.swe.buddybud.user.LoginActivity;

public class SignUpSndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_snd);

        // Language 스피너 설정
        Spinner spinnerLanguage = findViewById(R.id.spinnerLanguage);
        String[] languageArray = getResources().getStringArray(R.array.Language);
        setSpinner(spinnerLanguage, languageArray);

        // Gender 스피너 설정
        Spinner spinnerGender = findViewById(R.id.spinnerGender);
        String[] genderArray = getResources().getStringArray(R.array.Gender);
        setSpinner(spinnerGender, genderArray);

        // SignUp 버튼 클릭 시
        Button btnSignUp = findViewById(R.id.buttonSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // 스피너 설정
    private void setSpinner(Spinner spinner, String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line) {
            @Override
            public int getCount() {
                return super.getCount() - 1;        // 힌트 옵션 제외
            }
        };

        for (int i = 0; i < array.length; i++) {
            adapter.add(array[i]);
        }

        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());   // 초기값을 힌트로 설정
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // 클릭한 아이템의 텍스트 색상 변경
                TextView selectedTextView = (TextView) selectedItemView;
                selectedTextView.setTextColor(Color.parseColor("#A4A4A4"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView){}
        });
    }

}