package com.example.vocepolitico;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.vocepolitico.R.layout.questions_page;

public class QuestionsPageActivity extends QuestionsPageController {
    public int pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(questions_page);
        setupAll();

        btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuestions();
            }
        });

        seekbar_effect_multiply.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String v = String.valueOf(multiplyEffectValues(i));
                seekbar_value = multiplyEffectValues(i);
                textView.setText(v);

                if (pos > 0) {
                    tv_econ.setText(String.valueOf(Float.parseFloat(econ) * seekbar_value));
                    tv_dipl.setText(String.valueOf(Float.parseFloat(dipl) * seekbar_value));
                    tv_govt.setText(String.valueOf(Float.parseFloat(govt) * seekbar_value));
                    tv_scty.setText(String.valueOf(Float.parseFloat(scty) * seekbar_value));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void getQuestions()  {
        // Objeto das questoes
        ArrayList<String> questions_list = new ArrayList<>();
        String question_string = "";
        StringBuilder question_string_builder = new StringBuilder();
        InputStream questions_input_stream = this.getResources().openRawResource(R.raw.questions);
        BufferedReader questions_buffer_reader = new BufferedReader(new InputStreamReader(questions_input_stream));

        // Objeto dos valores effect
        ArrayList<String> effect_list = new ArrayList<>();
        String effect_string = "";
        StringBuilder effect_string_builder = new StringBuilder();
        InputStream effect_input_stream = this.getResources().openRawResource(R.raw.effect);
        BufferedReader effect_values_buffer_reader = new BufferedReader(new InputStreamReader(effect_input_stream));

        // Trata a string para os valores effect das questoes
        while (true) {
            try {
                if ((effect_string = effect_values_buffer_reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            effect_string_builder.append(effect_string);
            if (effect_string != "\n") {
                effect_list.add(effect_string);
                effect_string = "";
            }
        }

        // Trata a string para as Questoes
        while (true) {
            try {
                if ((question_string = questions_buffer_reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            question_string_builder.append(question_string);
            if (question_string != "\n") {
                questions_list.add(question_string);
                question_string = "";
            }
        }

        // Teste para nao quebrar o app (Nao excede o tamanho da lista)
        if (pos == effect_list.size()) pos = 0;

//        if (String.valueOf(questions.get(pos)).contains("econ")) {
        values = effect_list.get(pos).replaceAll(":", "").replaceAll("'", "").replace("{", "").replace("}", "");
        econ = values.substring(values.indexOf("econ ") + 5, values.indexOf(","));
        dipl = values.substring(values.indexOf("dipl ") + 5, values.indexOf(",", values.indexOf("dipl ")));
        govt = values.substring(values.indexOf("govt ") + 5, values.indexOf(",", values.indexOf("govt ")));
        scty = values.substring(values.indexOf("scty ") + 5);

        tv_questions.setText(" Question " + String.valueOf(pos + 1) + ": \n" + String.valueOf(questions_list.get(pos)));

        pos++;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public float multiplyEffectValues(Integer seekbar_value) {
                switch (seekbar_value) {
                    case 0:
                        effect_seekbar_value = -1;
                        user_opinion = "Discordo Fortemente";
                        break;
                    case 1:
                        effect_seekbar_value = (float) -0.5;
                        user_opinion = "Discordo";
                        break;
                    case 2:
                        effect_seekbar_value = 0;
                        user_opinion = "Neutro";
                        break;
                    case 3:
                        effect_seekbar_value = (float) 0.5;
                        user_opinion = "Concordo";
                        break;
                    case 4:
                        effect_seekbar_value = 1;
                        user_opinion = "Concordo Fortemente";
                        break;
                }

        return effect_seekbar_value;
    }
}

