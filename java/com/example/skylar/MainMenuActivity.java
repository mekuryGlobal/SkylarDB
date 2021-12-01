package com.example.skylar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.skylar.earn.EarnActivity;
import com.example.skylar.learn.CourseActivity;
import com.example.skylar.learn.CourseDetailActivity;
import com.example.skylar.learn.QuizActivity;
import com.example.skylar.model.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;


public class MainMenuActivity extends AppCompatActivity {

    private TextView mUsername;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;
    private DatabaseReference reference;
    private String firebaseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mUsername = findViewById(R.id.textUsername);

        FirebaseDatabase userDatabase = FirebaseDatabase.getInstance();
        reference = userDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseId = user.getUid();

        gettingDatabase();

    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

    public void profileClick(View view){
        LinearLayout linearLayout = findViewById(R.id.layoutProfile);
        Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void earnClick(View view){
        LinearLayout linearLayout = findViewById(R.id.layoutEarn);
        Intent intent = new Intent(MainMenuActivity.this, EarnActivity.class);
        startActivity(intent);
    }

    public void learnClick(View view){
        LinearLayout layoutLearn = findViewById(R.id.layoutLearn);
        Intent intent = new Intent(MainMenuActivity.this, CourseActivity.class);
        startActivity(intent);
    }

    public void opportunityClick(View view){
        LinearLayout layoutLearn = findViewById(R.id.layoutOpportunity);
        Intent intent = new Intent(MainMenuActivity.this, OpportunityActivity.class);
        startActivity(intent);
    }


    private void gettingDatabase(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    UserInformation information = new UserInformation();
                    information.setName(ds.child(firebaseId).getValue(UserInformation.class).getName());

                    StringBuilder theName = new StringBuilder(information.getName());
                    int end;
                    int i ;


                    for (i = 0;theName.charAt(i) != ' ' && i < (theName.length() - 1); ++i);

                    String theName2 = theName.substring(0,i+1);

                    mUsername.setText(theName2);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                Toast.makeText(MainMenuActivity.this,"Error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (listener != null){
            mAuth.removeAuthStateListener(listener);
        }
    }

}