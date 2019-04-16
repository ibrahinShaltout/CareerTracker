package com.example.ibrahimshaltout.test.tracks;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.TrackDataClass;

import java.util.ArrayList;

public class AddNewTrackAdapter extends RecyclerView.Adapter<AddNewTrackAdapter.AddNewTrackViewHolder> {


    private Context trackContext;
    private ArrayList<TrackDataClass> trackList;

    public AddNewTrackAdapter(Context mContext, ArrayList<TrackDataClass> tracks) {
        this.trackContext = mContext;
        this.trackList = tracks;
    }


    @NonNull
    @Override
    public AddNewTrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(trackContext)
                .inflate(R.layout.track_recommendation_item, viewGroup, false);
        return new AddNewTrackViewHolder(itemView);    }



    @Override
    public void onBindViewHolder(@NonNull AddNewTrackViewHolder addNewTrackViewHolder, int i) {
        TrackDataClass item = trackList.get(i);

        if (item.getTrackName() != null) {
            addNewTrackViewHolder.trackNameNew.setText(item.getTrackName());
        }
        if (item.getTrackBio() != null) {
            addNewTrackViewHolder.trackBioNew.setText(item.getTrackBio());
        }

        addNewTrackViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trackContext , TrackProfileActivity.class);
                    trackContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return trackList == null ? 0 : trackList.size();
    }

    class AddNewTrackViewHolder extends RecyclerView.ViewHolder {
        public TextView trackNameNew ;
        public TextView trackBioNew;
        public CardView cardView;
        AddNewTrackViewHolder(View view) {
            super(view);
            trackNameNew = (TextView) view.findViewById(R.id.name_of_track_new);
            trackBioNew = (TextView) view.findViewById(R.id.track_bio_new);
            cardView = (CardView) view.findViewById(R.id.card_view_track);


        }
    }
}
