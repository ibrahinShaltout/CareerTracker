package com.example.ibrahimshaltout.test.screens.people;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.IndividualDataClass;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    private RecyclerView people_recyclerView;

    IndividualAdapter individualAdapter;
    ArrayList<IndividualDataClass> PeopleIcon = new ArrayList<>();


    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.people_fragment, container, false);

        people_recyclerView = v.findViewById(R.id.recycler_view_people);
        individualAdapter = new IndividualAdapter(getActivity(), PeopleIcon);
        RecyclerView.LayoutManager peopleLayoutManager = new LinearLayoutManager(getActivity());
        int numberOfColumns=2;

        people_recyclerView.setLayoutManager(peopleLayoutManager);
        people_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        people_recyclerView.setNestedScrollingEnabled(true);
        people_recyclerView.setHasFixedSize(true);
        people_recyclerView.setAdapter(individualAdapter);

        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        PeopleIcon.add(new IndividualDataClass());
        individualAdapter.notifyDataSetChanged();

        return v;
    }

}
