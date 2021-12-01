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

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {

   private ArrayList<CategoryRVModel> categoryRVModels;
   private Context context;
   private CategoryClickInterface categoryClickInterface;

    public CategoryRVAdapter(ArrayList<CategoryRVModel> categoryRVModels, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVModels = categoryRVModels;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    public CategoryRVAdapter(ArrayList<CategoryRVModel> categoryRVModels, Context context) {
        this.categoryRVModels = categoryRVModels;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item, parent, false);
       return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryRVAdapter.ViewHolder holder, int position) {
        CategoryRVModel categoryRVModel = categoryRVModels.get(position);
        holder.tvCategoryView.setText(categoryRVModel.getCategory());
        Picasso.get().load(categoryRVModel.getCategoryImageUrl()).into(holder.imCategoryView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.OnCategoryClick(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRVModels.size();
    }

    public interface CategoryClickInterface{
       void OnCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategoryView;
       private ImageView imCategoryView;

        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            tvCategoryView = itemView.findViewById(R.id.tvCategoryView);
            imCategoryView = itemView.findViewById(R.id.imCategoryView);
        }
    }
}
