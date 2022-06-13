package com.example.dog_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myCommentsAdapter extends RecyclerView.Adapter<myCommentsAdapter.ViewHolder> {

    private List<Comments> commentsList;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    myCommentsAdapter adapter;
    private int id;

    public myCommentsAdapter(List<Comments> commentsList, int id) {
        this.commentsList = commentsList;
        this.id=id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(myCommentsAdapter.ViewHolder holder, int position) {

        final Comments commentsLists = commentsList.get(position);
        holder.email_comments.setText(commentsLists.getEmail());
        Date date = new Date(commentsLists.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.time_comments.setText(format.format(date));
        holder.text_comments.setText(commentsLists.getKomentar());
        holder.deletebtn_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                Call<Void> delete=retrofitInterface.deletecomment(commentsLists.getId());
                delete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        try{
                            commentsList.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                        }catch(Exception e){System.out.println(e);}
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t.toString());
                    }
                });

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
