package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.vocepolitico.R.layout.eight_values_explanation_01;

public class EightValuesExplanationOne extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(eight_values_explanation_01);
    }

    public void explanation_01_to_explanation_02(View view) {
        Intent intent = new Intent(this, EightValuesExplanationTwo.class);
        startActivity(intent);
    }
}
