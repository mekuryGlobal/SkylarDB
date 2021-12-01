package com.example.skylar;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class ProfileActivity extends AppCompatActivity {
    private Button btnLogout;
    private TextView mUsername;
    private TextView mEmail;
    private TextView mNumber;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;
    private FirebaseDatabase userDatabase;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String firebaseId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = findViewById(R.id.btn_logout);
        mUsername = findViewById(R.id.username);
        mEmail = findViewById(R.id.email);
        mNumber = findViewById(R.id.number);

        userDatabase = FirebaseDatabase.getInstance();
        reference = userDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        firebaseId = user.getUid();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        stateListener();
        gettingDatabase();

    }

    private void stateListener(){
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    Toast.makeText(ProfileActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                }

            }
        };


    }

    private void gettingDatabase(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    UserInformation information = new UserInformation();
                    information.setName(ds.child(firebaseId).getValue(UserInformation.class).getName());
                    information.setEmail(ds.child(firebaseId).getValue(UserInformation.class).getEmail());
                    information.setNumber(ds.child(firebaseId).getValue(UserInformation.class).getNumber());

                    mUsername.setText(information.getName());
                    mEmail.setText(information.getEmail());
                    mNumber.setText(information.getNumber());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,"Error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

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