package com.example.skylar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private CheckBox rememberMe;


    private Button btn_login;

    private SharedPreferences  loginPref;
    private SharedPreferences.Editor prefs;
    private boolean check;
    private  String email;
    private String password;

    private  FirebaseAuth mAuth;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TextView newUser;
        newUser = findViewById(R.id.new_user);
        String newUser2 = newUser.getText().toString();
        SpannableString span1 = new SpannableString(newUser2);



        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent1 = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent1);
            }

        };
        span1.setSpan(clickableSpan1,22,31,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span1.setSpan(new ForegroundColorSpan(getColor(R.color.colorPrimary)), 22,31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        newUser.setText(span1);
        newUser.setMovementMethod(LinkMovementMethod.getInstance());

        etEmail = findViewById(R.id.setEmail);
        etPassword = findViewById(R.id.setPassword);

        btn_login = findViewById(R.id.btn_login);
        rememberMe = findViewById(R.id.ck_remember);

        mAuth = FirebaseAuth.getInstance();
        loginPref = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        prefs = loginPref.edit();

        check = loginPref.getBoolean("savelogin",false);
        if (check == true){
            etEmail.setText(loginPref.getString("usernane", ""));
            etPassword.setText(loginPref.getString("password", ""));
            rememberMe.setChecked(true);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRememberMeMethod();
                loginMethod();

            }
        });

    }

    private void loginMethod() {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent1 = new Intent(SignInActivity.this, MainMenuActivity.class);
                    startActivity(intent1);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(SignInActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }}

    private void setRememberMeMethod() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etEmail.getWindowToken(),0);
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (rememberMe.isChecked()){
            prefs.putBoolean("savelogin",true);
            prefs.putString("username", email);
            prefs.putString("password", password);
            prefs.commit();
        }
        else {
            prefs.clear();
            prefs.commit();
        }
    }
}