package com.example.ibrahimshaltout.test.tracks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.ibrahimshaltout.test.R;

public class CoursesActivity extends AppCompatActivity {

    Toolbar toolbarTop;
    Button startProgress;
    int s = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        toolbarTop = findViewById(R.id.course_Screen_toolbar);
        setSupportActionBar(toolbarTop);
        toolbarTop.setTitleMarginStart(80);

        // add back arrow to main_tool_bar
        toolbarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });


        startProgress = (Button) findViewById(R.id.startProgress);

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == 1) {
                    startProgress.setText("In Progress");
                    s = 0;
                } else if (s == 0) {
                    startProgress.setText("Start");
                    s = 1;
                }
            }
        });


    }
}
