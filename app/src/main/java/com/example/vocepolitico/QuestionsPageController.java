package com.example.vocepolitico;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionsPageController extends AppCompatActivity {
    public int pos_question = 0;

    public static TextView tv_questions;
    public static TextView tv_econ;
    public static TextView tv_dipl;
    public static TextView tv_govt;
    public static TextView tv_scty;
    public static TextView question_position;
    public static TextView textView;
    public static SeekBar seekbar_effect_multiply;
    public static Float seekbar_value;

    public static String values;
    public static String econ;
    public static String dipl;
    public static String govt;
    public static String scty;

    public static Button btn_question;

    public static float effect_seekbar_value = 0;
    public static String user_opinion= "";

    public void setupAll() {
        question_position = findViewById(R.id.question_info);
        tv_questions = findViewById(R.id.question_box);
        btn_question = findViewById(R.id.btn_next_question);
        tv_econ = findViewById(R.id.value_econ);
        tv_dipl = findViewById(R.id.value_dipl);
        tv_govt = findViewById(R.id.value_govt);
        tv_scty = findViewById(R.id.value_scty);
        seekbar_effect_multiply = findViewById(R.id.seekbar_choice);
        textView = findViewById(R.id.teste);
    }
}
