package com.example.skylar.earn;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skylar.R;
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


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class EarnActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;
    private DatabaseReference reference;
    private String firebaseId;
    private String publicKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn);

        FirebaseDatabase userDatabase = FirebaseDatabase.getInstance();
        reference = userDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseId = user.getUid();

        gettingDatabase();

    }

    public void gettingDatabase(){

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    UserInformation information = new UserInformation();
                    information.setPublicKey(ds.child(firebaseId).getValue(UserInformation.class).getPublicKey());
                    String publicKey  = information.getPublicKey() ;

                    Button btnCreate = findViewById(R.id.buttonCreate);
                    btnCreate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent;

                            try {
                                if(publicKey.equals("nothing")){
                                    intent = new Intent(EarnActivity.this, CreateWalletActivity.class);
                                }
                                else {
                                    intent = new Intent(EarnActivity.this, AccountActivity2.class);
                                }
                                startActivity(intent);
                            }
                            catch(NullPointerException e) {
                                System.out.println(publicKey);
                            }

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                Toast.makeText(EarnActivity.this,"Error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}