package com.example.ibrahimshaltout.test.screens.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ibrahimshaltout.test.R;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahimshaltout.test.screens.notification.firebase_notification.Config;
import com.example.ibrahimshaltout.test.screens.notification.firebase_notification.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;


public class NotificationActivity extends AppCompatActivity {

    private RecyclerView notification_recyclerView;
    private NotificationAdapter notificationAdapter;
    Toolbar toolbarTop;
    TabHost host;

    ArrayList<Notification> notifications = new ArrayList<>();

    private static final String TAG = NotificationActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbarTop = findViewById(R.id.notification_top_bar);
        setSupportActionBar(toolbarTop);
        toolbarTop.setTitle("Message");
        toolbarTop.setTitleMarginStart(80);

        // add back arrow to main_tool_bar
        toolbarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("All").setContent(R.id.notification_tab1).setIndicator("All");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("TrackDataClass").setContent(R.id.notification_tab2).setIndicator("TrackDataClass");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Opportunities").setContent(R.id.notification_tab3).setIndicator("Chance");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("App Team").setContent(R.id.notification_tab4).setIndicator("AppTeam");
        host.addTab(spec);


        txtRegId = (TextView) findViewById(R.id.txt_reg_id);
        txtMessage = (TextView) findViewById(R.id.txt_push_message);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();



        notification_recyclerView = findViewById(R.id.recycler_view_notification_screen);
        notificationAdapter = new NotificationAdapter(this, notifications);
        RecyclerView.LayoutManager trackLayoutManager = new LinearLayoutManager(this);
        notification_recyclerView.setLayoutManager(trackLayoutManager);
        notification_recyclerView.setNestedScrollingEnabled(true);
        notification_recyclerView.setHasFixedSize(true);
//        tracks_recyclerView.requestFocus();
        notification_recyclerView.setAdapter(notificationAdapter);

        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());

        notificationAdapter.notifyDataSetChanged();

    }


    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}