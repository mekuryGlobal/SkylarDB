package com.example.skylar.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.skylar.MainMenuActivity;
import com.example.skylar.R;
import com.example.skylar.model.CourseModel;
import com.example.skylar.model.CategoryRVAdapter;
import com.example.skylar.model.CategoryRVModel;
import com.example.skylar.model.ContentModel;
import com.example.skylar.model.ContentRVAdapter;
import com.example.skylar.model.RetrofitAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface {

    //3ca7df735ba24136b064d2455868e63e

    private RecyclerView content, category;
    private ProgressBar loadingPB;
    private ArrayList<CourseModel> courseModelArrayList;
    private ArrayList<CategoryRVModel> categoryRVModelArrayList;
    CategoryRVAdapter categoryRVAdapter;
    private ContentRVAdapter contentRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        category = findViewById(R.id.courseCategories);
        content = findViewById(R.id.contentView);
        loadingPB = findViewById(R.id.contentProgress);
        courseModelArrayList = new ArrayList<>();
        categoryRVModelArrayList = new ArrayList<>();
        contentRVAdapter = new ContentRVAdapter(courseModelArrayList, this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModelArrayList,this, this::OnCategoryClick);
        content.setLayoutManager(new LinearLayoutManager(this));
        content.setAdapter(contentRVAdapter);
        category.setAdapter(categoryRVAdapter);
        getCategories();
        getContent("All");
        contentRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CourseActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void getCategories(){
        categoryRVModelArrayList.add(new CategoryRVModel("All","https://skylar3.s3.eu-west-2.amazonaws.com/All.jpeg"));
        categoryRVModelArrayList.add(new CategoryRVModel("Earth Observation","https://skylar3.s3.eu-west-2.amazonaws.com/Earth+Observation+from+Space_The+Optical+View.jpeg.jpeg"));
        categoryRVModelArrayList.add(new CategoryRVModel("COVID-19 Atmospheric Composition","https://skylar3.s3.eu-west-2.amazonaws.com/Impact+of+COVID-19+measures+on+Atmospheric+Composition.png"));
        categoryRVModelArrayList.add(new CategoryRVModel("Catastrophe Modelling","https://skylar3.s3.eu-west-2.amazonaws.com/Catastrophe+Modelling.jpeg"));
        categoryRVModelArrayList.add(new CategoryRVModel("New Space","https://skylar3.s3.eu-west-2.amazonaws.com/Earth+Observation_Disruptive+Technology+and+New+Space.jpeg"));
        categoryRVModelArrayList.add(new CategoryRVModel("Atmospheric Composition","https://skylar3.s3.eu-west-2.amazonaws.com/Monitoring+Atmospheric+Composition.jpeg"));
        categoryRVModelArrayList.add(new CategoryRVModel("Monitoring Climate","https://skylar3.s3.eu-west-2.amazonaws.com/Monitoring+Climate+from+Space.jpeg"));
        categoryRVModelArrayList.add(new CategoryRVModel("Monitoring Oceans","https://skylar3.s3.eu-west-2.amazonaws.com/Monitoring+Oceans+from+Space.jpeg"));
        categoryRVModelArrayList.add(new CategoryRVModel("Monitoring Ice sheets","https://skylar3.s3.eu-west-2.amazonaws.com/The+Frozen+Frontier_Monitoring+the+Greenland+Ice+Sheet+from+Space.jpeg"));
        categoryRVAdapter.notifyDataSetChanged();
    }


    private void getContent(String category){
        loadingPB.setVisibility(View.VISIBLE);
        courseModelArrayList.clear();
        String eoURL = "https://skylar3.s3.eu-west-2.amazonaws.com/course+categories/Earth+Observation.json";
        String cacURL = "https://skylar3.s3.eu-west-2.amazonaws.com/course+categories/COVID-19+Atmospheric+Composition.json";
        String categoryURL = "https://skylar3.s3.eu-west-2.amazonaws.com/course+categories/All+category.json";
        if (category.equals("Earth Observation")){
            categoryURL = eoURL;}

        if (category.equals("COVID-19 Atmospheric Composition")){

            categoryURL = cacURL;}

        String url = "https://skylar3.s3.eu-west-2.amazonaws.com/course+categories/All+category.json";
        String BASE_URL = "https://skylar3.s3.eu-west-2.amazonaws.com/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<ContentModel> call;

        if (category.equals("All")){
            call = retrofitAPI.getAllContent(url);
        }
            else{
            call = retrofitAPI.getContentByCategory(categoryURL);
        }
            call.enqueue(new Callback<ContentModel>() {
                @Override
                public void onResponse(Call<ContentModel> call, Response<ContentModel> response) {
                    ContentModel contentModel = response.body();
                    loadingPB.setVisibility(View.GONE);
                    ArrayList<CourseModel> articles = contentModel.getArticles();
                    for (int i = 0; i < articles.size(); i++ ){
                        courseModelArrayList.add(new CourseModel(articles.get(i).getTitle(), articles.get(i).getDescription(), articles.get(i).getUrlToImage(), articles.get(i).getContent()));

                    }

                    contentRVAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ContentModel> call, Throwable t) {
                    Toast.makeText(CourseActivity.this, "Failed to get courses", Toast.LENGTH_SHORT).show();


                }
            });
        }



    @Override
    public void OnCategoryClick(int position) {
        String category = categoryRVModelArrayList.get(position).getCategory();
        getContent(category);

    }
}