package com.example.vocepolitico;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.vocepolitico.R.layout.eight_values_explanation_02;

public class EightValuesExplanationTwo extends AppCompatActivity {

    SeekBar seekBar;
    TextView choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(eight_values_explanation_02);

        seekBar = findViewById(R.id.barra_escolha);
        choice = findViewById(R.id.text_choice);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = 0;
                String opinion= "";
                switch(i) {
                    case 0:
                        value = -1;
                        opinion = "Discordo Fortemente";
                        break;
                    case 1:
                        value = (float) -0.5;
                        opinion = "Discordo";
                        break;
                    case 2:
                        value = 0;
                        opinion = "Neutro";
                        break;
                    case 3:
                        value = (float) 0.5;
                        opinion = "Concordo";
                        break;
                    case 4:
                        value = 1;
                        opinion = "Concordo Fortemente";
                        break;
                }
                choice.setText(opinion);
                //Toast.makeText(EightValuesExplanationTwo.this, opinion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}
