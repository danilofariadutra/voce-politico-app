package com.example.vocepolitico;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeScroll;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import static com.example.vocepolitico.R.layout.activity_splash_screen_01;
import static com.example.vocepolitico.R.layout.activity_splash_screen_02;

public class SplashScreens extends AppCompatActivity {

    private static Scene scSplashScreen02 = null;
    private static Scene scSplashScreen03 = null;
    private static Scene scMainActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_01);

        ViewGroup root = findViewById(R.id.sc_splash_screen_root);
        scSplashScreen02 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_02, this);

//        ViewGroup vgSplashScreen02 = findViewById(R.id.sc_splash_screen_02);
        scSplashScreen03 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_03, this);

//        ViewGroup vgSplashScreen03 = findViewById(R.id.sc_splash_screen_03);
        scMainActivity = Scene.getSceneForLayout(root, R.layout.activity_main, this);
    }

    public void splash_01_to_splash_screen_02(View view) {
        Transition slide = new AutoTransition();
        slide.setDuration(2750);

        TransitionManager.go(scSplashScreen02, slide);
    }

    public void splash_screen_02_to_splash_screen_03(View view) {
        Transition slide = new AutoTransition();
        slide.setDuration(2750);

        TransitionManager.go(scSplashScreen03, slide);
    }

    public void splash_screen_03_to_main(View view) {
        Transition slide = new AutoTransition();
        slide.setDuration(2750);

        TransitionManager.go(scMainActivity, slide);
    }

    public void splash_screen(View view) {
        Transition slide = new AutoTransition();
        slide.setDuration(2000);

        TransitionManager.go(scSplashScreen02, slide);
    }
}
