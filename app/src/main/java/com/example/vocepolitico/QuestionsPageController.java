package com.example.vocepolitico;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionsPageController extends AppCompatActivity {

    public static TextView tv_questions;
    public static Button btn_question;

    public void setupAll() {
        tv_questions = findViewById(R.id.question);
        btn_question = findViewById(R.id.btn_questions);
    }
}
