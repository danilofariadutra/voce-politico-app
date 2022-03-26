package com.example.vocepolitico;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.ChangeScroll;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import static com.example.vocepolitico.R.layout.activity_splash_screen_01;
import static com.example.vocepolitico.R.layout.activity_splash_screen_02;

public class MainActivity extends AppCompatActivity {

    private static Scene scSplashScreen01 = null;
    private static Scene scSplashScreen02 = null;
    private static Scene scSplashScreen03 = null;
    private static Scene scMainActivity = null;
    private static int SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen_01);

        ViewGroup root = findViewById(R.id.sc_splash_screen_root);

        // Cria os objetos do tipo Scene
        scSplashScreen01 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_01, this);
        scSplashScreen02 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_02, this);
        scSplashScreen03 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_03, this);
        scMainActivity = Scene.getSceneForLayout(root, R.layout.activity_main, this);

        // Cria a transicao para a primeira Splash Screen na onCreate
        Transition transition;
        transition = new AutoTransition();
        transition.setDuration(2000);
        transition.setInterpolator(new AnticipateInterpolator());
        TransitionManager.go(scSplashScreen01, transition);
    }

    public void splash_01_to_splash_screen_02(View view) {
        Transition slide = new Slide(Gravity.RIGHT);
        slide.setDuration(1000);

        TransitionManager.go(scSplashScreen02, slide);
    }

    public void splash_screen_02_to_splash_screen_03(View view) {
        Transition slide = new Slide(Gravity.RIGHT);
        slide.setDuration(1000);

        TransitionManager.go(scSplashScreen03, slide);
    }

    public void splash_screen_03_to_main(View view) {
        Transition slide = new Slide(Gravity.RIGHT);
        slide.setDuration(1000);

        TransitionManager.go(scMainActivity, slide);
    }

    public void splash_screen(View view) {
        Transition slide = new AutoTransition();
        slide.setDuration(1500);

        TransitionManager.go(scSplashScreen02, slide);
    }
}
