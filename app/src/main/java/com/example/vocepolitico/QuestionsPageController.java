package com.example.vocepolitico;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionsPageController extends AppCompatActivity {
    public int posQuestion = 0;

    SharedPreferences userValues;
    SharedPreferences.Editor userValuesEditor;

    public static TextView tvQuestions;
    public static TextView tvEcon;
    public static TextView tvDipl;
    public static TextView tvGovt;
    public static TextView tvScty;
    public static TextView questionPosition;
    public static TextView textView;
    public static SeekBar seekbarEffectMultiply;
    public static Float seekbarValue;

    public static String values;
    public static String econ;
    public static String dipl;
    public static String govt;
    public static String scty;

    public static Button btnQuestion;

    public static float effectSeekbarValue = 0;
    public static String userOpinion = "";

    public void setupAll() {
        questionPosition = findViewById(R.id.question_info);
        tvQuestions = findViewById(R.id.question_box);
        btnQuestion = findViewById(R.id.btn_next_question);
        tvEcon = findViewById(R.id.value_econ);
        tvDipl = findViewById(R.id.value_dipl);
        tvGovt = findViewById(R.id.value_govt);
        tvScty = findViewById(R.id.value_scty);
        seekbarEffectMultiply = findViewById(R.id.seekbar_choice);
        textView = findViewById(R.id.teste);
    }
}
