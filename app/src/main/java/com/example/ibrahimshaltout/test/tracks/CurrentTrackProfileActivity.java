package com.example.ibrahimshaltout.test.tracks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ibrahimshaltout.test.MainActivity;
import com.example.ibrahimshaltout.test.R;

public class CurrentTrackProfileActivity extends AppCompatActivity {

//    LinearLayout linearLayout1;
//    TextView main_station_one;
//    private int show_hide1 = 0;
//
//    TextView note;
//
//    LinearLayout linearLayout2;
//    TextView main_station_two;
//    private int show_hide2 = 0;
//
//
//    LinearLayout linearLayout3;
//    TextView main_station_Three;
//    private int show_hide3 = 0;

    Toolbar toolbarTop;


    Button coursebutton_a1;
    Button coursebutton_a2;

    LinearLayout linear_JAva_1;
    Button buttom_JAva_1;
    private int show_hidea11 = 1;

    LinearLayout linear_a1;
    Button buttom_a1;
    private int show_hidea1 = 0;

    LinearLayout linear_b1;
    Button buttom_b1;
    private int show_hidea2 = 0;

    LinearLayout linear_c1;
    Button buttom_c1;
    private int show_hidea3 = 0;

    LinearLayout linear_D1;
    Button buttom_D1;
    private int show_hidea4 = 0;


    LinearLayout linear_Android_2;
    Button buttom_Android_2;
    private int show_hidea22 = 1;


    LinearLayout linear_a2;
    Button buttom_a2;
    private int show_hidea5 = 0;

    LinearLayout linear_b2;
    Button buttom_b2;
    private int show_hidea6 = 0;


    LinearLayout linear_c2;
    Button buttom_c2;
    private int show_hidea7 = 0;


    LinearLayout linear_d2;
    Button buttom_d2;
    private int show_hidea8 = 0;


    LinearLayout linear_Firebase_3;
    Button buttom_Firebase_3;
    private int show_hidea33 = 1;

    LinearLayout linear_a3;
    Button buttom_a3;
    private int show_hidea9 = 0;

    LinearLayout linear_d3;
    Button buttom_d3;
    private int show_hidea10 = 0;

    CheckBox c1;
    Button t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_track_profile);

        c1 = (CheckBox) findViewById(R.id.c1);
        t1 = (Button) findViewById(R.id.t1);


//        c1.setonClickListener=new onClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                if(checkBox.isChecked())
//                    yourTextView.setVisibility(View.VISIBLE);
//                else
//                    yourTextView.setVisibility(View.GONE);
//
//            }
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (c1.isChecked())
                    t1.setVisibility(View.GONE);
                else
                    t1.setVisibility(View.VISIBLE);

            }
        });


        toolbarTop = findViewById(R.id.Current_Track_profile_Screen);
        setSupportActionBar(toolbarTop);
        toolbarTop.setTitleMarginStart(80);

        // add back arrow to main_tool_bar
        toolbarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CurrentTrackProfileActivity.this, MainActivity.class));
                finish();
            }
        });

        coursebutton_a1 = (Button) findViewById(R.id.coursebutton_a1);
        coursebutton_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CurrentTrackProfileActivity.this, CoursesActivity.class));

            }
        });
        coursebutton_a2 = (Button) findViewById(R.id.coursebutton_a2);
        coursebutton_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CurrentTrackProfileActivity.this, Courses2Activity.class));

            }
        });

        linear_JAva_1 = (LinearLayout) findViewById(R.id.linear_JAva_1);
        buttom_JAva_1 = (Button) findViewById(R.id.buttom_JAva_1);
        buttom_JAva_1.setRotation(180);
        linear_JAva_1.setVisibility(View.GONE);

        buttom_JAva_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea11 == 1) {
                    linear_JAva_1.setVisibility(View.GONE);
                    show_hidea11 = 0;
                    buttom_JAva_1.setRotation(180);

                } else {
                    linear_JAva_1.setVisibility(View.VISIBLE);
                    show_hidea11 = 1;
                    buttom_JAva_1.setRotation(360);
                }
            }
        });

        linear_a1 = (LinearLayout) findViewById(R.id.linear_a1);
        buttom_a1 = (Button) findViewById(R.id.buttom_a1);
        buttom_a1.setRotation(180);
        linear_a1.setVisibility(View.GONE);

        buttom_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea1 == 1) {
                    linear_a1.setVisibility(View.GONE);
                    show_hidea1 = 0;
                    buttom_a1.setRotation(180);

                } else {
                    linear_a1.setVisibility(View.VISIBLE);
                    show_hidea1 = 1;
                    buttom_a1.setRotation(360);
                }
            }
        });


        linear_b1 = (LinearLayout) findViewById(R.id.linear_b1);
        buttom_b1 = (Button) findViewById(R.id.buttom_b1);
        buttom_b1.setRotation(180);
        linear_b1.setVisibility(View.GONE);

        buttom_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea2 == 1) {
                    linear_b1.setVisibility(View.GONE);
                    show_hidea2 = 0;
                    buttom_b1.setRotation(180);

                } else {
                    linear_b1.setVisibility(View.VISIBLE);
                    show_hidea2 = 1;
                    buttom_b1.setRotation(360);
                }
            }
        });

        linear_c1 = (LinearLayout) findViewById(R.id.linear_c1);
        buttom_c1 = (Button) findViewById(R.id.buttom_c1);
        buttom_c1.setRotation(180);
        linear_c1.setVisibility(View.GONE);

        buttom_c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea3 == 1) {
                    linear_c1.setVisibility(View.GONE);
                    show_hidea3 = 0;
                    buttom_c1.setRotation(180);

                } else {
                    linear_c1.setVisibility(View.VISIBLE);
                    show_hidea3 = 1;
                    buttom_c1.setRotation(360);
                }
            }
        });

        linear_D1 = (LinearLayout) findViewById(R.id.linear_D1);
        buttom_D1 = (Button) findViewById(R.id.buttom_D1);
        buttom_D1.setRotation(180);
        linear_D1.setVisibility(View.GONE);

        buttom_D1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea4 == 1) {
                    linear_D1.setVisibility(View.GONE);
                    show_hidea4 = 0;
                    buttom_D1.setRotation(180);

                } else {
                    linear_D1.setVisibility(View.VISIBLE);
                    show_hidea4 = 1;
                    buttom_D1.setRotation(360);
                }
            }
        });


        linear_Android_2 = (LinearLayout) findViewById(R.id.linear_Android_2);
        buttom_Android_2 = (Button) findViewById(R.id.buttom_Android_2);
        buttom_Android_2.setRotation(180);
        linear_Android_2.setVisibility(View.GONE);

        buttom_Android_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea22 == 1) {
                    linear_Android_2.setVisibility(View.GONE);
                    show_hidea22 = 0;
                    buttom_Android_2.setRotation(180);

                } else {
                    linear_Android_2.setVisibility(View.VISIBLE);
                    show_hidea22 = 1;
                    buttom_Android_2.setRotation(360);
                }
            }
        });


        linear_a2 = (LinearLayout) findViewById(R.id.linear_a2);
        buttom_a2 = (Button) findViewById(R.id.buttom_a2);
        buttom_a2.setRotation(180);
        linear_a2.setVisibility(View.GONE);

        buttom_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea5 == 1) {
                    linear_a2.setVisibility(View.GONE);
                    show_hidea5 = 0;
                    buttom_a2.setRotation(180);

                } else {
                    linear_a2.setVisibility(View.VISIBLE);
                    show_hidea5 = 1;
                    buttom_a2.setRotation(360);
                }
            }
        });


        linear_b2 = (LinearLayout) findViewById(R.id.linear_b2);
        buttom_b2 = (Button) findViewById(R.id.buttom_b2);
        buttom_b2.setRotation(180);
        linear_b2.setVisibility(View.GONE);

        buttom_b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea6 == 1) {
                    linear_b2.setVisibility(View.GONE);
                    show_hidea6 = 0;
                    buttom_b2.setRotation(180);

                } else {
                    linear_b2.setVisibility(View.VISIBLE);
                    show_hidea6 = 1;
                    buttom_b2.setRotation(360);
                }
            }
        });


        linear_c2 = (LinearLayout) findViewById(R.id.linear_c2);
        buttom_c2 = (Button) findViewById(R.id.buttom_c2);
//        buttom_c2.setRotation(180);
        linear_c2.setVisibility(View.VISIBLE);

        buttom_c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea7 == 1) {
                    linear_c2.setVisibility(View.GONE);
                    show_hidea7 = 0;
                    buttom_c2.setRotation(180);

                } else {
                    linear_c2.setVisibility(View.VISIBLE);
                    show_hidea7 = 1;
                    buttom_c2.setRotation(360);
                }
            }
        });


        linear_d2 = (LinearLayout) findViewById(R.id.linear_d2);
        buttom_d2 = (Button) findViewById(R.id.buttom_d2);
        buttom_d2.setRotation(180);
        linear_d2.setVisibility(View.GONE);

        buttom_d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea8 == 1) {
                    linear_d2.setVisibility(View.GONE);
                    show_hidea8 = 0;
                    buttom_d2.setRotation(180);

                } else {
                    linear_d2.setVisibility(View.VISIBLE);
                    show_hidea8 = 1;
                    buttom_d2.setRotation(360);
                }
            }
        });


        linear_Firebase_3 = (LinearLayout) findViewById(R.id.linear_Firebase_3);
        buttom_Firebase_3 = (Button) findViewById(R.id.buttom_Firebase_3);
        linear_Firebase_3.setRotation(180);
        linear_Firebase_3.setVisibility(View.GONE);

        buttom_Firebase_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea33 == 1) {
                    linear_Firebase_3.setVisibility(View.GONE);
                    show_hidea33 = 0;
                    buttom_Firebase_3.setRotation(180);

                } else {
                    linear_Firebase_3.setVisibility(View.VISIBLE);
                    show_hidea33 = 1;
                    buttom_Firebase_3.setRotation(360);
                }
            }
        });


        linear_a3 = (LinearLayout) findViewById(R.id.linear_a3);
        buttom_a3 = (Button) findViewById(R.id.buttom_a3);
        buttom_a3.setRotation(180);
        linear_a3.setVisibility(View.GONE);

        buttom_a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea9 == 1) {
                    linear_a3.setVisibility(View.GONE);
                    show_hidea9 = 0;
                    buttom_a3.setRotation(180);

                } else {
                    linear_a3.setVisibility(View.VISIBLE);
                    show_hidea9 = 1;
                    buttom_a3.setRotation(360);
                }
            }
        });


        linear_d3 = (LinearLayout) findViewById(R.id.linear_d3);
        buttom_d3 = (Button) findViewById(R.id.buttom_d3);
        buttom_d3.setRotation(180);
        linear_d3.setVisibility(View.GONE);

        buttom_d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hidea10 == 1) {
                    linear_d3.setVisibility(View.GONE);
                    show_hidea10 = 0;
                    buttom_d3.setRotation(180);

                } else {
                    linear_d3.setVisibility(View.VISIBLE);
                    show_hidea10 = 1;
                    buttom_d3.setRotation(360);
                }
            }
        });


//        note = (TextView) findViewById(R.id.note);
//        note.setVisibility(View.VISIBLE);

//        note.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                note.setVisibility(View.GONE);
//
//            }
//        });


////        linearLayout1.setVisibility(View.GONE);
//        linearLayout1 = (LinearLayout) findViewById(R.id.Current_linear1);
//        main_station_one = (TextView) findViewById(R.id.Current_main_station_one);
//
//        linearLayout2 = (LinearLayout) findViewById(R.id.Current_linear2);
//        main_station_two = (TextView) findViewById(R.id.Current_main_station_two);
//
//        linearLayout3 = (LinearLayout) findViewById(R.id.Current_linear3);
//        main_station_Three = (TextView) findViewById(R.id.Current_main_station_three);
//
//        linearLayout1.setVisibility(View.GONE);
//        linearLayout2.setVisibility(View.GONE);
//        linearLayout3.setVisibility(View.GONE);
//
//        main_station_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (show_hide1 == 1) {
//                    linearLayout1.setVisibility(View.GONE);
//                    show_hide1 = 0;
//                } else {
//                    linearLayout1.setVisibility(View.VISIBLE);
//                    show_hide1 = 1;
//                }
//            }
//        });
//
//        main_station_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (show_hide2 == 1) {
//                    linearLayout2.setVisibility(View.GONE);
//                    show_hide2 = 0;
//                } else {
//                    linearLayout2.setVisibility(View.VISIBLE);
//                    show_hide2 = 1;
//                }
//            }
//        });
//
//        main_station_Three.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (show_hide3 == 1) {
//                    linearLayout3.setVisibility(View.GONE);
//                    show_hide3 = 0;
//                } else {
//                    linearLayout3.setVisibility(View.VISIBLE);
//                    show_hide3 = 1;
//                }
//            }
//        });
    }
}
