package com.swe.buddybud.account;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.swe.buddybud.R;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.user.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountEditActivity extends AppCompatActivity {

    private TextView userIdTextview;
    private EditText userGenderEdit;
    private Spinner languageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        userIdTextview = findViewById(R.id.user_id_edit);
        String userId = LoginData.getLoginUserId();
        userIdTextview.setText(userId);

        userGenderEdit = findViewById(R.id.user_gender_edit);
        languageSpinner = findViewById(R.id.language_spinner);
        Button doneButton = findViewById(R.id.done_button);

        ImageButton goBackButton = findViewById(R.id.go_back_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish and go back to the previous activity
            }
        });

        // Set up the done button to collect the data and send it to the server
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect data from fields
                String userId = LoginData.getLoginUserId();
                String language = languageSpinner.getSelectedItem().toString();
                String gender = userGenderEdit.getText().toString();

                // Create an instance of UserAccountUpdate
                UserAccountUpdate updateInfo = new UserAccountUpdate(userId, language, gender);

                // Send data to server
                updateUserAccount(userId, updateInfo);
            }
        });
    }

    private void updateUserAccount(String userId, UserAccountUpdate updateInfo) {
        AccountApiService service = RetrofitClient.getService(AccountApiService.class);
        Call<Void> call = service.updateUserAccount(userId, updateInfo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AccountEditActivity.this, "Account updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                } else {
                    Toast.makeText(AccountEditActivity.this, "Failed to update account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AccountEditActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
