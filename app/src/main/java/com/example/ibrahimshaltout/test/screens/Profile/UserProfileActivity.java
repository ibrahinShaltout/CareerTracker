package com.example.ibrahimshaltout.test.screens.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ibrahimshaltout.test.R;

public class UserProfileActivity extends AppCompatActivity {

    Toolbar toolbarTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbarTop = findViewById(R.id.user_profile_top_bar);
        setSupportActionBar(toolbarTop);
        toolbarTop.setTitle(" ");
        toolbarTop.setTitleMarginStart(80);

        // add back arrow to main_tool_bar
        toolbarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

    }
}
