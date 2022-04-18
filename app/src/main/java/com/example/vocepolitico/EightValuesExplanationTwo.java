package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.vocepolitico.R.layout.eight_values_explanation_02;

public class EightValuesExplanationTwo extends AppCompatActivity {

    SeekBar seekBarExample;
    TextView choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(eight_values_explanation_02);

        seekBarExample = findViewById(R.id.seekbar_example);
        choice = findViewById(R.id.text_choice);

        QuestionsPageActivity.changeTextView(QuestionsPageActivity.returnMultiplyEffectText(2), choice);

        seekBarExample.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String opinion= QuestionsPageActivity.returnMultiplyEffectText(i);
                QuestionsPageActivity.changeTextView(opinion, choice);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void goToTest(View view) {
        Intent intent = new Intent(this, QuestionsPageActivity.class);
        startActivity(intent);
    }
}
