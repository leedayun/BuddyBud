package com.swe.buddybud.user;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.swe.buddybud.ContentActivity;
import com.swe.buddybud.R;

public class LoginActivity extends AppCompatActivity {

    // 화면 복귀 시 박스 초기화
    @Override
    protected void onResume() {
        super.onResume();

        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        CheckBox checkBoxRemember = findViewById(R.id.checkBoxRememberMe);

        editTextEmail.setText(LoginData.loginRemember ? LoginData.loginEmail : "");
        editTextPassword.setText("");
        checkBoxRemember.setChecked(LoginData.loginRemember);
    }

    @Override
    protected void onPause() {
        super.onPause();

        EditText editTextEmail = findViewById(R.id.editTextEmail);
        LoginData.loginEmail = editTextEmail.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CheckBox checkBoxRemember = findViewById(R.id.checkBoxRememberMe);
        Button btnSignIn = findViewById(R.id.buttonSignIn);
        TextView textViewSignUp = findViewById(R.id.textViewSignUp);

        // Remember me 체크 박스 클릭시
        checkBoxRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginData.loginRemember = checkBoxRemember.isChecked();
            }
        });

        // Sign In 버튼 클릭 시 : ContentActivity
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPassword = findViewById(R.id.editTextPassword);

                if (editTextEmail.getText().length() == 0 || editTextPassword.getText().length() == 0) {
                    makeText(getApplicationContext(), "Please fill the form",Toast.LENGTH_LONG);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Sign Up 버튼 클릭시 : SignUpFstActivity
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpFstActivity.class);
                startActivity(intent);
            }
        });
    }
}