package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.vocepolitico.R.layout.activity_splash_screen_02;

public class SplashScreen02 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_02);
    }

    public void splash_screen_03(View view) {
        Intent intent = new Intent(SplashScreen02.this, SplashScreen03.class);
        startActivity(intent);
        finish();
    }

    public void splash_screen_02(View view) {
        Intent intent = new Intent(SplashScreen02.this, SplashScreen01.class);
        startActivity(intent);
        finish();
    }
}
