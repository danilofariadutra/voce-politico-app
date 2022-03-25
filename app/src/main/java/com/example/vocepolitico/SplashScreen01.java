package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import static com.example.vocepolitico.R.layout.activity_splash_screen_01;

public class SplashScreen01 extends AppCompatActivity {

//    ImageButton btnNextSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_01);

//        btnNextSplashScreen = findViewById(R.id.btn_right_splash_screen_01);
//
//        btnNextSplashScreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Animation animation = AnimationUtils.loadAnimation(SplashScreen01.this, R.anim.mixed_anim);
//                btnNextSplashScreen.startAnimation(animation);
//
//                Intent intent = new Intent(SplashScreen01.this, SplashScreen02.class);
//                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.move_animation, R.anim.move_animation);
//                ActivityCompat.startActivity(SplashScreen01.this, intent, activityOptionsCompat.toBundle());
//                finish();
//            }
//        });
    }

    public void splash_screen_02(View view) {
        Intent intent = new Intent(SplashScreen01.this, SplashScreen02.class);
        startActivity(intent);
        finish();
    }

    public void main_activity(View view) {
        Intent intent = new Intent(SplashScreen01.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
