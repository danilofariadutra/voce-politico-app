package com.example.vocepolitico;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import static com.example.vocepolitico.R.layout.activity_splash_screen_01;

public class SplashScreen01 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_01);
    }
}
