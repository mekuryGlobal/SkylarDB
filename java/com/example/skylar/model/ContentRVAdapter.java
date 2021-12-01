package com.example.skylar.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylar.R;
import com.example.skylar.learn.CourseDetailActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ContentRVAdapter extends RecyclerView.Adapter<ContentRVAdapter.ViewHolder> {
    private ArrayList<CourseModel> courseModelArrayList;
    private Context context;

    public ContentRVAdapter(ArrayList<CourseModel> courseModelArrayList, Context context) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ContentRVAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item, parent, false);
        return new ContentRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContentRVAdapter.ViewHolder holder, int position) {
        CourseModel courseModel = courseModelArrayList.get(position);
        holder.tvContentSubheading.setText(courseModel.getDescription());
        holder.tvContentView.setText(courseModel.getTitle());
        Picasso.get().load(courseModel.getUrlToImage()).into(holder.ivContentView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("title", courseModel.getTitle());
                intent.putExtra("description", courseModel.getDescription());
                intent.putExtra("content", courseModel.getContent());
                intent.putExtra("image", courseModel.getUrlToImage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContentView,tvContentSubheading;
        private ImageView ivContentView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvContentView = itemView.findViewById(R.id.tvContentView);
            tvContentSubheading = itemView.findViewById(R.id.tvContentSubheading);
            ivContentView = itemView.findViewById(R.id.ivContentView);
        }
    }
}
