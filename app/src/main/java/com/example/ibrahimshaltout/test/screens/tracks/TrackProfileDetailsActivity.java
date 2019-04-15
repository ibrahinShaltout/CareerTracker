package com.example.ibrahimshaltout.test.screens.tracks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.PathDataClass;

import java.util.ArrayList;

public class TrackProfileDetailsActivity extends AppCompatActivity {

   private RecyclerView RecyclerView_Paths;
   private PathAdapter pathAdapter;
   Button track_Start;

    ArrayList<PathDataClass> pathDataClasses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_profile_details);

        RecyclerView_Paths =findViewById(R.id.RecyclerView_Paths);
        pathAdapter = new PathAdapter(this,pathDataClasses);
        final RecyclerView.LayoutManager pathLayoutManager = new LinearLayoutManager(this);
        RecyclerView_Paths.setLayoutManager(pathLayoutManager);
        RecyclerView_Paths.setNestedScrollingEnabled(true);
        RecyclerView_Paths.setHasFixedSize(true);
        RecyclerView_Paths.setAdapter(pathAdapter);

        pathDataClasses.add(new PathDataClass());
        pathDataClasses.add(new PathDataClass());
        pathDataClasses.add(new PathDataClass());

        pathAdapter.notifyDataSetChanged();

        track_Start =(Button)findViewById(R.id.choose_path);
        track_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivitiesMethod();

            }
        });
    }

    private void startActivitiesMethod() {
        startActivity(new Intent(this, ChoosePathActivity.class));
    }
}
