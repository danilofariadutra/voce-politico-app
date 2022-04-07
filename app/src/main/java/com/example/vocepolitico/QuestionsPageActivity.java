package com.example.vocepolitico;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.vocepolitico.R.layout.questions_page;
import static java.lang.Float.parseFloat;

public class QuestionsPageActivity extends QuestionsPageController {
    private Boolean didCreate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(questions_page);
        didCreate = true;
//        setupUI(); // Configura a Content
        setupAll(); // Configura os objetos do Controller
        getQuestions(posQuestion);
//        posQuestion ++;

//        if (posQuestion == 0) {
//            questionPosition.setText("Questão 0" + String.valueOf(posQuestion + 1) + " de 70");
//            seekbarValue = multiplyEffectValues(seekbarEffectMultiply.getProgress());
//
//            try {
//                tvEcon.setText(String.valueOf(parseFloat( econ) * seekbarValue));
//                tvDipl.setText(String.valueOf(parseFloat(dipl) * seekbarValue));
//                tvGovt.setText(String.valueOf(parseFloat(govt) * seekbarValue));
//                tvScty.setText(String.valueOf(parseFloat(scty) * seekbarValue));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }

        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                didCreate = false;
                getQuestions(posQuestion);
            }
        });

        seekbarEffectMultiply.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String v = String.valueOf(multiplyEffectValues(i));
                seekbarValue = multiplyEffectValues(i);
                textView.setText(v);
                Toast.makeText(QuestionsPageActivity.this, String.valueOf(posQuestion), Toast.LENGTH_SHORT).show();
//                Log.i("Valores", values);
//                Log.i("Multiply", String.valueOf(parseFloat( econ) * seekbarValue) + " " + String.valueOf(parseFloat(dipl) * seekbarValue) + " " + String.valueOf(parseFloat(govt) * seekbarValue) + " " + String.valueOf(parseFloat(scty) * seekbarValue));
//                Log.i("posQuestion", String.valueOf(posQuestion));
//                if (userEffectValues.size() > 0) {
//                    Log.i("Array", String.valueOf(userEffectValues.get(posQuestion)));
//                }
//                Log.i("userEffectValues.size", String.valueOf(userEffectValues.size()));
                Toast.makeText(QuestionsPageActivity.this, String.valueOf(didCreate), Toast.LENGTH_SHORT).show();
//                if (didCreate) didCreate = false;


//                if (posQuestion > 0) {
                    tvEcon.setText(String.valueOf(parseFloat( econ) * seekbarValue));
                    tvDipl.setText(String.valueOf(parseFloat(dipl) * seekbarValue));
                    tvGovt.setText(String.valueOf(parseFloat(govt) * seekbarValue));
                    tvScty.setText(String.valueOf(parseFloat(scty) * seekbarValue));
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void getQuestions(Integer posQuestion)  {
        Toast.makeText(this, String.valueOf(posQuestion), Toast.LENGTH_SHORT).show();

//        if (posQuestion > 3) Toast.makeText(this, "Acabou", Toast.LENGTH_LONG).show();

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
//        Log.i("PosQuestion A", String.valueOf(posQuestion));
        if (posQuestion == effect_list.size()) posQuestion = 0;
        values = effect_list.get(posQuestion).replaceAll(":", "").replaceAll("'", "").replace("{", "").replace("}", "");
//        Log.i("Valores", values);
//        Log.i("Questão", questions_list.get(posQuestion));


         econ = values.substring(values.indexOf("econ ") + 5, values.indexOf(","));
        dipl = values.substring(values.indexOf("dipl ") + 5, values.indexOf(",", values.indexOf("dipl ")));
        govt = values.substring(values.indexOf("govt ") + 5, values.indexOf(",", values.indexOf("govt ")));
        scty = values.substring(values.indexOf("scty ") + 5);
//        Log.i("Values: ", String.valueOf(values + 1));

//        Log.i("Array String: ", (userEffectValues.get(posQuestion)));


        tvQuestions.setText(questions_list.get(posQuestion));
        if (posQuestion < 9) {
            questionPosition.setText("Questão 0" + String.valueOf(posQuestion + 1) + " de 70");
        } else {
            questionPosition.setText("Questão " + String.valueOf(posQuestion + 1) + " de 70");
        }

//        userEffectValues.add(econ, dipl, govt, scty);
//        String n = econ + ", " + dipl + ", " + govt + ", " + scty;

//        Log.i("seekbarValue: ", String.valueOf(seekbarValue));
//        Log.i("Values", values);
        ArrayList<Float> floats = new ArrayList<Float>();
//        Log.i("SeekBarValue", String.valueOf(seekbarValue));
        if (seekbarEffectMultiply.getProgress() == 2) seekbarValue = 1f;

        if (didCreate == false) {
            floats.addAll(Arrays.asList(parseFloat(econ) * seekbarValue, parseFloat(dipl) * seekbarValue, parseFloat(govt) * seekbarValue, parseFloat(scty) * seekbarValue));
            String floatsWithoutMultiply = "econ " + econ + " dipl " + dipl + " govt " + govt + " scty " + scty;
//        Log.i("Floats: ", String.valueOf(floats));
//        Log.i("Values Without Multiply", floatsWithoutMultiply);
//        userEffectValues.addAll(Arrays.asList(Float.parseFloat(econ), Float.parseFloat(dipl), Float.parseFloat(govt), Float.parseFloat(scty)));
            userEffectValues.addAll(Arrays.asList(floats));
            getEffectResult(userEffectValues);
        }

//        Log.i("Parse Float pos: " + String.valueOf(posQuestion), String.valueOf(userEffectValues.get(posQuestion)));
        seekbarEffectMultiply.setProgress(2);
//        floats.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public float multiplyEffectValues(Integer seekbar_value) {
                switch (seekbar_value) {
                    case 0:
                        effectSeekbarValue = -1;
                        userOpinion = "Discordo Fortemente";
                        break;
                    case 1:
                        effectSeekbarValue = (float) -0.5;
                        userOpinion = "Discordo";
                        break;
                    case 2:
                        effectSeekbarValue = 0;
                        userOpinion = "Neutro";
                        break;
                    case 3:
                        effectSeekbarValue = (float) 0.5;
                        userOpinion = "Concordo";
                        break;
                    case 4:
                        effectSeekbarValue = 1;
                        userOpinion = "Concordo Fortemente";
                        break;
                    default:
                        effectSeekbarValue = 0;
                        userOpinion = "Neutro";
                        break;
                }

        return effectSeekbarValue;
    }

    public void getEffectResult(ArrayList<ArrayList<Float>> result) {
        for (int pos = 0; pos < result.size(); pos ++) {
            Log.i("Value " + String.valueOf(pos), String.valueOf(result));
        }
    }
}

