package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import static com.example.vocepolitico.R.layout.activity_splash_screen_01;

public class MainActivity extends MainController {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_01);
        setupAll();
        initFirstTransition();
    }

    public void splash_01_to_splash_screen_02 (View view) {
        Transition slide = new Slide(Gravity.LEFT);
        slide.setDuration(1750);

        TransitionManager.go(scSplashScreen02, slide);
    }

    public void splash_screen_02_to_splash_screen_03 (View view) {
        Transition slide = new Slide(Gravity.RIGHT);
        slide.setDuration(1750);

        TransitionManager.go(scSplashScreen03, slide);
    }

    public void splash_screen_03_to_main (View view) {
        Transition transition;
        transition = new AutoTransition();
        transition.setDuration(2000);
        transition.setInterpolator(new AnticipateInterpolator());

        TransitionManager.go(scMainActivity, transition);
    }

    public void go_to_explanation (View view){
        Intent intent = new Intent(this, EightValuesExplanationOne.class);
        startActivity(intent);
    }

    public void initFirstTransition() {
        Transition transition;
        transition = new AutoTransition();
        transition.setDuration(2000);
        transition.setInterpolator(new AnticipateInterpolator());
        TransitionManager.go(scSplashScreen01, transition);
    }
}
