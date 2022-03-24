package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.vocepolitico.R.layout.activity_splash_screen_01;

public class SplashScreen01 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_01);
    }

    public void splash_screen_02(View view) {
        Intent intent = new Intent(SplashScreen01.this, SplashScreen02.class);
        startActivity(intent);
        finish();
    }
}
