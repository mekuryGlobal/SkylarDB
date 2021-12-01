package com.example.skylar.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skylar.R;
import com.squareup.picasso.Picasso;

public class CourseDetailActivity extends AppCompatActivity {

    String title, desc, content, imageURL, url;
    private TextView idIVTitle, idTVSub, idTVContent;
    private ImageView idIVCourse;
    private Button completeCourse, takeQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("description");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("image");


        idIVTitle = findViewById(R.id.idIVTitle);
        idTVSub = findViewById(R.id.idTVSub);
        idTVContent = findViewById(R.id.idTVContent);
        idIVCourse = findViewById(R.id.idIVCourse);
        completeCourse = findViewById(R.id.completeCourse);
        takeQuiz = findViewById(R.id.takeQuiz);

        idIVTitle.setText(title);
        idTVSub.setText(desc);
        idTVContent.setText(content);
        Picasso.get().load(imageURL).into(idIVCourse);
        completeCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });

        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CourseDetailActivity.this, CourseActivity.class);
        startActivity(intent);
        finish();
    }
}