package com.example.ibrahimshaltout.test.newsfeed.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.newsfeed.post.PostDataClass;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    Context mContext;
    private ArrayList<PostDataClass> commentsList;

    public CommentAdapter(Context mContext, ArrayList<PostDataClass> commentsList) {
        this.commentsList = commentsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.comment_list_item, viewGroup, false);
        return new CommentAdapter.CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        PostDataClass item = commentsList.get(i);
        commentViewHolder.comment.setText(item.getComment());
//        commentViewHolder.comment_owner.setText(item.getWriterName());
    }

    @Override
    public int getItemCount() {
        return commentsList == null ? 0 : commentsList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
//        TextView comment_owner;
        TextView comment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = (TextView) itemView.findViewById(R.id.comment);
//            comment_owner = (TextView) itemView.findViewById(R.id.comment_owner);


        }
    }
}
