package com.example.skylar.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.skylar.R;
import com.example.skylar.model.QuizModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    private TextView question, numberOfQuestions;
    private Button option1,option2,option3,option4;
    private ArrayList<QuizModel> quizModelArrayList;

    Random random;
    int currentScore = 0, questionsAnswered = 1, currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        findViewById(R.id.idTBalanceDollar);

        question = findViewById(R.id.idQuestion);
        numberOfQuestions = findViewById(R.id.questionAttempted);

        option1 = findViewById(R.id.idOption1);
        option2 = findViewById(R.id.idOption2);
        option3 = findViewById(R.id.idOption3);
        option4 = findViewById(R.id.idOption4);

        quizModelArrayList = new ArrayList<>();
        random = new Random();

        getQuizQuestion(quizModelArrayList);
        currentPos = random.nextInt(quizModelArrayList.size());
        setDataToView(currentPos);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAnswered++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToView(currentPos);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAnswered++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToView(currentPos);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAnswered++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToView(currentPos);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAnswered++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToView(currentPos);
            }
        });


    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuizActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet, (LinearLayout) findViewById(R.id.idScoreLayout));
        TextView idScore = bottomSheetView.findViewById(R.id.idScore);
        Button idRestart = bottomSheetView.findViewById(R.id.idRestart);
        Button idExit = bottomSheetView.findViewById(R.id.idExit);
        idScore.setText("Your score is \n" + currentScore + "/10");
        idRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToView(currentPos);
                questionsAnswered = 1;
                currentScore = 0;

            }
        });

        idExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CourseDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });



        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    private void setDataToView(int currentPos) {
        numberOfQuestions.setText("Question Attempted:" + questionsAnswered + "/10");
        if (questionsAnswered == 10){
            showBottomSheet();
        }
        else{
            question.setText(quizModelArrayList.get(currentPos).getQuestion());
            option1.setText(quizModelArrayList.get(currentPos).getOption1());
            option2.setText(quizModelArrayList.get(currentPos).getOption2());
            option3.setText(quizModelArrayList.get(currentPos).getOption3());
            option4.setText(quizModelArrayList.get(currentPos).getOption4());
        }

    }



    private void getQuizQuestion(ArrayList<QuizModel> quizModelArrayList) {
        quizModelArrayList.add(new QuizModel("What term describes the alignment of three celestial bodies", "sizzle", "suzerainity", "syzygy", "symbology", "syzygy"));
        quizModelArrayList.add(new QuizModel("Which of these objects is farthest from the sun", "Kuiper belt", "Neptune", "90377 Sedna", "Saturn", "90377 Sedna"));
        quizModelArrayList.add(new QuizModel("What two motions do all planets have", "twist and shout", "rock and roll", "wiggle and wobble", "orbit and spin", "orbit and spin"));
        quizModelArrayList.add(new QuizModel("Approximately how many miles or kilometers) are there in a light-year", "5.9 trillion (9.5 trillion km)", "5.9 million (9.5 million km)", "5.9 billion (9.5 billion km)", "5950,000 (950,000 km)", "5.9 trillion (9.5 trillion km)"));
    }
}