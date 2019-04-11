package com.example.ibrahimshaltout.test.screens.messages;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ibrahimshaltout.test.R;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private FloatingActionButton addNewMessageButton;

    private RecyclerView Message_recyclerView;
    private MessageAdapter messageAdapter;
    Toolbar toolbarTop;


    ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        toolbarTop = findViewById(R.id.message_top_bar);
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

        addNewMessageButton = (FloatingActionButton) findViewById(R.id.add_new_message_button);
        addNewMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddNewTrackActivity.class);
//                startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        Message_recyclerView = findViewById(R.id.recycler_view_message_screen);
        messageAdapter = new MessageAdapter(this, messages);
        RecyclerView.LayoutManager trackLayoutManager = new LinearLayoutManager(this);
        Message_recyclerView.setLayoutManager(trackLayoutManager);
        Message_recyclerView.setNestedScrollingEnabled(true);
        Message_recyclerView.setHasFixedSize(true);
//        tracks_recyclerView.requestFocus();
        Message_recyclerView.setAdapter(messageAdapter);

        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
        messageAdapter.notifyDataSetChanged();

    }
}
