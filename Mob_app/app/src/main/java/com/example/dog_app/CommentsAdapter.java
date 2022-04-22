package com.example.dog_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private List<Comments> commentsList;

    public CommentsAdapter(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position) {

        final Comments commentsLists = commentsList.get(position);
        holder.email_comments.setText(commentsLists.getEmail());
        Date date = new Date(commentsLists.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.time_comments.setText(format.format(date));
        holder.text_comments.setText(commentsLists.getKomentar());
        holder.deletebtn_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentsList.remove(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView email_comments, time_comments, text_comments;
        public MaterialButton deletebtn_comments;

        public ViewHolder(View itemView) {
            super(itemView);
            email_comments=(TextView) itemView.findViewById(R.id.email_comments);
            time_comments=(TextView) itemView.findViewById(R.id.time_comments);
            text_comments=(TextView) itemView.findViewById(R.id.text_comments);
            deletebtn_comments=(MaterialButton) itemView.findViewById(R.id.deletebtn_comments);
        }
    }
}
