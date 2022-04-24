package com.example.vocepolitico;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import static com.example.vocepolitico.R.layout.activity_splash_screen_01;

public class MainActivity extends MainController {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_01);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setupAll();
        initFirstTransition();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void splash01ToSplashScreen02(View view) {
        Transition slide = new Fade();
        slide.setDuration(1750);

        TransitionManager.go(scSplashScreen02, slide);
    }

    public void splashScreen02ToSplashScreen03(View view) {
        Transition slide = new Fade();
        slide.setDuration(1750);

        TransitionManager.go(scSplashScreen03, slide);
    }

    public void splashScreen03ToExplanation(View view) {
//        Transition slide;
//        slide = new MaterialFade();
//        slide.setDuration(3500);
////        slide.setInterpolator(new AnticipateInterpolator());
//
//        TransitionManager.go(scQuestionExplanation, slide);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                Intent intent = new Intent(MainActivity.this, EightValuesExplanationOne.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
//            }
//        }, 2500);
    }

    public void initFirstTransition() {
        Transition transition;
        transition = new AutoTransition();
        transition.setDuration(2000);
        transition.setInterpolator(new AnticipateInterpolator());
        TransitionManager.go(scSplashScreen01, transition);
    }
}
