package com.example.vocepolitico;

import android.transition.AutoTransition;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

public class MainModel extends MainController {
    public static void initTransition() {
        // Cria a transicao para a primeira Splash Screen na onCreate
        Transition transition;
        transition = new AutoTransition();
        transition.setDuration(2000);
        transition.setInterpolator(new AnticipateInterpolator());
        TransitionManager.go(scSplashScreen01, transition);
    }

    public static void splash_01_to_splash_screen_02(View view) {
        Transition slide = new Slide(Gravity.LEFT);
        slide.setDuration(1750);

        TransitionManager.go(scSplashScreen02, slide);
    }

    public void splash_screen_02_to_splash_screen_03(View view) {


        Transition slide = new Slide(Gravity.RIGHT);
        slide.setDuration(1750);

        TransitionManager.go(scSplashScreen03, slide);
    }

    public void splash_screen_03_to_main(View view) {
        Transition transition;
        transition = new AutoTransition();
        transition.setDuration(2000);
        transition.setInterpolator(new AnticipateInterpolator());

        TransitionManager.go(scMainActivity, transition);
    }

    public void splash_screen(View view) {
        Transition transition;
        transition = new AutoTransition();
        transition.setDuration(2500);
        transition.setInterpolator(new AnticipateInterpolator());
        TransitionManager.go(scSplashScreen02, transition);
    }
}
