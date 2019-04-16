package com.example.ibrahimshaltout.test.tracks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.PathDataClass;

import java.util.ArrayList;

public class ChoosePathActivity extends AppCompatActivity {

    private RecyclerView RecyclerView_Paths;
    private PathAdapter pathAdapter;
    ArrayList<PathDataClass> pathDataClasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_path);

        RecyclerView_Paths =findViewById(R.id.choose_path);
        pathAdapter = new PathAdapter(this,pathDataClasses);
        RecyclerView.LayoutManager pathLayoutManager1 = new LinearLayoutManager(this);
        RecyclerView_Paths.setLayoutManager(pathLayoutManager1);
        RecyclerView_Paths.setNestedScrollingEnabled(true);
        RecyclerView_Paths.setHasFixedSize(true);
        RecyclerView_Paths.setAdapter(pathAdapter);

        pathDataClasses.add(new PathDataClass());
        pathDataClasses.add(new PathDataClass());
        pathDataClasses.add(new PathDataClass());

        pathAdapter.notifyDataSetChanged();


    }
}
