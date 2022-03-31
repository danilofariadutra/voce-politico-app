package com.example.vocepolitico;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionsPageController extends AppCompatActivity {

    public static TextView tv_questions;
    public static TextView tv_econ;
    public static TextView tv_dipl;
    public static TextView tv_govt;
    public static TextView tv_scty;

    public static Button btn_question;

    public void setupAll() {
        tv_questions = findViewById(R.id.question);
        btn_question = findViewById(R.id.btn_questions);
        tv_econ = findViewById(R.id.value_econ);
        tv_dipl = findViewById(R.id.value_dipl);
        tv_govt = findViewById(R.id.value_govt);
        tv_scty = findViewById(R.id.value_scty);
    }
}
