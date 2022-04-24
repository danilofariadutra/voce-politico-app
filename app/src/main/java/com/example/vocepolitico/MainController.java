package com.example.vocepolitico;

import android.transition.Scene;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainController extends AppCompatActivity {
    public static Scene scSplashScreen01, scSplashScreen02, scSplashScreen03, scMainActivity, scQuestionExplanation = null;
    public ImageButton btnScSplash01;
    public ImageButton btnScSplash02;
    public ImageButton btnScSplash03;

    public void setupAll() {
        setupTransitions();
        setupComponents();
    }

    public void setupComponents() {
        btnScSplash01 = findViewById(R.id.btn_right_splash_screen_01);
        btnScSplash02 = findViewById(R.id.btn_right_splash_screen_02);
        btnScSplash03 = findViewById(R.id.btn_right_splash_screen_03);
    }

    public void setupTransitions() {
        ViewGroup root = findViewById(R.id.sc_splash_screen_root);

        // Cria os objetos do tipo Scene
        scSplashScreen01 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_01, this);
        scSplashScreen02 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_02, this);
        scSplashScreen03 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_03, this);
        scQuestionExplanation = Scene.getSceneForLayout(root, R.layout.eight_values_explanation_01, this);
    }
}
