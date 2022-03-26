package com.example.vocepolitico;

import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.vocepolitico.MainModel.splash_01_to_splash_screen_02;

public class MainController extends AppCompatActivity {

    protected static Scene scSplashScreen01, scSplashScreen02, scSplashScreen03, scMainActivity = null;
    protected ImageButton btn_splash_screen_01, btn_splash_screen_02, btn_splash_screen_03;

    public void init() {
        ViewGroup root = findViewById(R.id.sc_splash_screen_root);

        // Cria os objetos do tipo Scene
        scSplashScreen01 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_01, this);
        scSplashScreen02 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_02, this);
        scSplashScreen03 = Scene.getSceneForLayout(root, R.layout.activity_splash_screen_03, this);
        scMainActivity = Scene.getSceneForLayout(root, R.layout.activity_main, this);

        btn_splash_screen_01 = findViewById(R.id.btn_right_splash_screen_01);
        btn_splash_screen_02 = findViewById(R.id.btn_right_splash_screen_02);
        btn_splash_screen_03 = findViewById(R.id.btn_right_splash_screen_03);

        btn_splash_screen_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splash_01_to_splash_screen_02(view);
            }
        });
    }
}
