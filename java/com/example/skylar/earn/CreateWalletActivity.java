package com.example.skylar.earn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.skylar.R;

public class CreateWalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);
        Button createWallet = findViewById(R.id.btn_create_wallet);
        createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateWalletActivity.this, CreateTheAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}