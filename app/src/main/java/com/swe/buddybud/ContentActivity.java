package com.swe.buddybud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.swe.buddybud.account.AccountFragment;
import com.swe.buddybud.board.BoardFragment;
import com.swe.buddybud.home.HomeFragment;
import com.swe.buddybud.willow.WillowFragment;

public class ContentActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        chipNavigationBar = findViewById(R.id.ChipNavigationBar);
        chipNavigationBar.setItemSelected(R.id.action_home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new HomeFragment()).commit();
        bottomMenu();
    }
    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.action_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.action_board:
                        fragment = new BoardFragment();
                        break;
                    case R.id.action_willow:
                        fragment = new WillowFragment();
                        break;
                    case R.id.action_account:
                        fragment = new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();
            }
        });
    }
}