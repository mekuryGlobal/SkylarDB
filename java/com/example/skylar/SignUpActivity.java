package com.example.skylar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skylar.model.UserInformation;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SignUpActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etname;
    private EditText etnumber;
    private Button btn_register;

    //firebase
    private FirebaseDatabase userInfoDatabase;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
     

    private  static  final  String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView oldUser;
        oldUser = findViewById(R.id.old_user);
        String newUser2 = oldUser.getText().toString();
        SpannableString span1 = new SpannableString(newUser2);
        oldUser.setText(span1);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent1 = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent1);
                finish();
            }

        };
        span1.setSpan(clickableSpan1,25,30,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span1.setSpan(new ForegroundColorSpan(getColor(R.color.colorPrimary)), 25,30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        oldUser.setText(span1);
        oldUser.setMovementMethod(LinkMovementMethod.getInstance());

        etEmail = findViewById(R.id.setEmail);
        etPassword = findViewById(R.id.setPassword);
        etname = findViewById(R.id.setName);
        etnumber = findViewById(R.id.setNumber);

        userInfoDatabase = FirebaseDatabase.getInstance();
        reference = userInfoDatabase.getReference("users");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        checkInternetConnection();



    }

    private void checkInternetConnection(){

        ConnectivityManager check = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (check != null)
        {
            NetworkInfo info = check.getActiveNetworkInfo();
            if (info != null && info.isConnectedOrConnecting())

                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        btn_register = findViewById(R.id.btn_register);
                        btn_register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                authenticationListener();


                            }
                        });


                    }

        }
        else{
            Toast.makeText(this, "not conencted to internet",
                    Toast.LENGTH_SHORT).show();
        }
    }







    private void authenticationListener(){
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show();
        }
        else{

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull  @NotNull Task<AuthResult> task) {

                    UserInformation userInformation = new UserInformation();
                    userInformation.setEmail(email);
                    userInformation.setPassword(password);
                    userInformation.setName(etname.getText().toString());
                    userInformation.setNumber(etnumber.getText().toString());
                    userInformation.setPublicKey("nothing");



                    Intent intent1 = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent1);
                    finish();



                    reference.child(currentUser.getUid()).setValue(userInformation).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void avoid) {
                            Toast.makeText(SignUpActivity.this, "Registration was successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(SignUpActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }



}