package com.example.ibrahimshaltout.test.newsfeed.track;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.TrackDataClass;

import java.util.ArrayList;

public class TrackAdapterRecommendation extends RecyclerView.Adapter<TrackAdapterRecommendation.NewsFeedTrackViewHolder> {

    private Context trackContext;
    private ArrayList<TrackDataClass> trackList;

    public TrackAdapterRecommendation(Context mContext, ArrayList<TrackDataClass> tracks) {
        this.trackContext = mContext;
        this.trackList = tracks;
    }


    @NonNull
    @Override
    public NewsFeedTrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(trackContext)
                .inflate(R.layout.track_bar_item, viewGroup, false);
        return new NewsFeedTrackViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedTrackViewHolder newsFeedViewHolder, int position) {
        TrackDataClass item = trackList.get(position);

        if (item.getTrackName() != null) {
            newsFeedViewHolder.trackName.setText(item.getTrackName());
        }
        if (item.getTrackBio() != null) {
            newsFeedViewHolder.trackBio.setText(item.getTrackBio());
        }
    }

    @Override
    public int getItemCount() {
        return trackList == null ? 0 : trackList.size();
    }

    class NewsFeedTrackViewHolder extends RecyclerView.ViewHolder {
        public TextView trackName ;
        public TextView trackBio ;

        NewsFeedTrackViewHolder(View view) {
            super(view);
            trackName = (TextView) view.findViewById(R.id.name_of_track);
            trackBio = (TextView) view.findViewById(R.id.track_bio);

        }
    }
}
