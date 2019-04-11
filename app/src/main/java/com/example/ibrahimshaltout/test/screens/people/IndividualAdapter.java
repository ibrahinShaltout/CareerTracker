package com.example.ibrahimshaltout.test.screens.people;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.IndividualDataClass;

import java.util.ArrayList;

public class IndividualAdapter extends RecyclerView.Adapter<IndividualAdapter.PeopleViewHolder> {


    private Context peopleContext;
    private ArrayList<IndividualDataClass> People_Icon_list;

    public IndividualAdapter(Context mContext, ArrayList<IndividualDataClass> People_Icon) {
        this.peopleContext = mContext;
        this.People_Icon_list = People_Icon;
    }


    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(peopleContext)
                .inflate(R.layout.people_item, viewGroup, false);

        return new PeopleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder peopleViewHolder, int position) {

//        final IndividualDataClass item = People_Icon.get(position);
//
//        peopleViewHolder.name.setText(item.fristName);

    }

    @Override
    public int getItemCount() {return People_Icon_list == null ? 0 : People_Icon_list.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);

//            name = (TextView) itemView.findViewById(R.id.people_name);

        }
    }
}
