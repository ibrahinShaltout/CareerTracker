package com.example.ibrahimshaltout.test.screens.newsfeed.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ibrahimshaltout.test.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.NewsFeedPostViewHolder>{

    private Context trackContext;
    private ArrayList<PostDataClass> posts;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final String key = database.getReference("Posts").getKey();

    public PostAdapter(Context mContext, ArrayList<PostDataClass> posts) {
        this.trackContext = mContext;
        this.posts = posts;
    }

    @NonNull
    @Override
    public NewsFeedPostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(trackContext)
                .inflate(R.layout.post_item, viewGroup, false);

        return new NewsFeedPostViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final NewsFeedPostViewHolder newsFeedViewHolder, final int position) {

        final PostDataClass item = posts.get(position);

        newsFeedViewHolder.desc.setText(item.getPostData());
        if (item.getPostName() != null) {
            newsFeedViewHolder.profileName.setText(item.getPostName());
        }
        if (item.getTimeAndDate() != null) {
            newsFeedViewHolder.dateAndTime.setText(item.getTimeAndDate());
        }

        newsFeedViewHolder.number_of_like.setText(item.getNumber_of_likes()+"");


        newsFeedViewHolder.thumbs_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posts.get(position).setNumber_of_likes(posts.get(position).getNumber_of_likes()+1);
                newsFeedViewHolder.number_of_like.setText(String.valueOf(posts.get(position).getNumber_of_likes()));
                int n = posts.get(position).getNumber_of_likes();
                FirebaseDatabase.getInstance().getReference("Posts").child(item.getPost_ID())
                        .child("number_of_likes")
                        .setValue(n);
            }
        });
//        newsFeedViewHolder.number_of_like.setText(String.valueOf(posts.get(position).getCount()));

//        if (item.getImageURL() != null) {
//            newsFeedViewHolder.imageView.setImageURI(item.getPostPhotoUrl());
//        PostDataClass UploadInfo = ImagePostList.get(position);
//        newsFeedViewHolder.imageNameTextView.setText(UploadInfo.getImageName());}
        //Loading image from Glide library.

        Glide.with(trackContext).load(item.getImageURL()).into(newsFeedViewHolder.imageView);
//
    }
    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

//    @Override
//    public void onClick(View v) {
//        thumbs_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int id = (int)thumbs_up.getTag();
//                if( id == R.drawable.ic_thumb_up){
//
//                    thumbs_up.setTag(R.drawable.ic_thumb_up);
//                    thumbs_up.setImageResource(R.drawable.ic_thumb_up);
//
//                } else {
//
//                }
//
//            }
//        });
//    }

    class NewsFeedPostViewHolder extends RecyclerView.ViewHolder {
        public TextView desc;
        public TextView profileName;
        public TextView dateAndTime;
        public ImageView imageView;
        public TextView number_of_like;
        public ImageButton thumbs_up;


        NewsFeedPostViewHolder(View view) {
            super(view);
            desc = (TextView) view.findViewById(R.id.txtStatusMsgPost);
            profileName = (TextView) view.findViewById(R.id.Profile_name);
            dateAndTime =(TextView) view.findViewById(R.id.timestamp);
            imageView = (ImageView) view.findViewById(R.id.post_image);
            number_of_like = (TextView) view.findViewById(R.id.Number_Of_Likes);
            thumbs_up= (ImageButton)view.findViewById(R.id.thumbs_up);

        }
    }
}
