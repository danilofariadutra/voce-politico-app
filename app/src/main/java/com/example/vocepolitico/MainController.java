package com.example.vocepolitico;

import android.transition.Scene;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainController extends AppCompatActivity {

    public static Scene scSplashScreen01, scSplashScreen02, scSplashScreen03, scMainActivity, scQuestionExplanation = null;

    public void setupAll() {
        setupTransitions();
    }

    public void setupTransitions() {
        ViewGroup root = findViewById(R.id.sc_splash_screen_root);

        // Cria os objetos do tipo Scene
        scSplashScreen01 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_01, this);
        scSplashScreen02 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_02, this);
        scSplashScreen03 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_03, this);
        scMainActivity = Scene.getSceneForLayout(root, R.layout.activity_main, this);
        scQuestionExplanation = Scene.getSceneForLayout(root, R.layout.eight_values_explanation_01, this);
    }
}
