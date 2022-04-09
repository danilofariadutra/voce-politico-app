package com.example.vocepolitico;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
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
        setupAll();
        showQuestionsOnDisplay(posQuestion);

        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserValues();

                posQuestion ++;

                avoidCrashActivity();
                showQuestionsOnDisplay(posQuestion);
            }
        });

        seekbarEffectMultiply.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String v = String.valueOf(multiplyEffectValues(i));
                seekbarValue = multiplyEffectValues(i);
                changeTextView(v, textView);

                changeTextView(String.valueOf(parseFloat(econ) * seekbarValue), tvEcon);
                changeTextView(String.valueOf(parseFloat(dipl) * seekbarValue), tvDipl);
                changeTextView(String.valueOf(parseFloat(govt) * seekbarValue), tvGovt);
                changeTextView(String.valueOf(parseFloat(scty) * seekbarValue), tvScty);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public boolean avoidCrashActivity() {
        int sizeOfArrayQuestions = getQuestionsFromJSON().size();
        if (posQuestion >= sizeOfArrayQuestions) {
            Toast.makeText(QuestionsPageActivity.this, String.valueOf(posQuestion), Toast.LENGTH_LONG).show();
            posQuestion = 0;
            return true;
        }
        return false;
    }

    // Formata as linhas de questoes vindas do arquivo .json e retorna uma ArrayList<String>
    public ArrayList<String> getQuestionsFromJSON() {
        ArrayList<String> questions_list = new ArrayList<>();
        String question_string = "";
        StringBuilder question_string_builder = new StringBuilder();
        InputStream questions_input_stream = this.getResources().openRawResource(R.raw.questions);
        BufferedReader questions_buffer_reader = new BufferedReader(new InputStreamReader(questions_input_stream));

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
        return questions_list;
    }

    // Formata os valores econ, dipl, govt e scty vindos do arquivo .json e retorna uma ArrayList<String>
    public ArrayList<String> getEffectValuesFromJSON() {
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
        return effect_list;
    }

    // Exibe todas as informacoes na Activity
    public void showQuestionsOnDisplay(Integer pos)  {
        setInitialSeekbarProgress();
        parseEffectValuesToFloat();
        changeAllTextViews();
        }

    public void setInitialSeekbarProgress() {
        seekbarEffectMultiply.setProgress(2);
        if (seekbarEffectMultiply.getProgress() == 2) seekbarValue = 0f;
    }

    // Quebra os valores da ArrayList<String> com os 4 valores (econ, dipl, govt, scty), e repassa para econ, dipl, govt, scty
    public void parseEffectValuesToFloat() {
        ArrayList<String> arrayEffectValues = getEffectValuesFromJSON();

        values = arrayEffectValues.get(posQuestion).replaceAll(":", "").replaceAll("'", "").replace("{", "").replace("}", "");

        econ = values.substring(values.indexOf("econ ") + 5, values.indexOf(","));
        dipl = values.substring(values.indexOf("dipl ") + 5, values.indexOf(",", values.indexOf("dipl ")));
        govt = values.substring(values.indexOf("govt ") + 5, values.indexOf(",", values.indexOf("govt ")));
        scty = values.substring(values.indexOf("scty ") + 5);

        Log.i("Parse Multiply Values", "Seekbar: " + String.valueOf(seekbarValue) + " | econ: " + String.valueOf(econ) + " | dipl: " + String.valueOf(dipl) + " | govt: " + String.valueOf(govt) + " | scty: " + String.valueOf(scty));
    }

    // Mostra os valores econ, dipl, govt e scty, multiplicando pelo valor da seekbar e mostra a questão em TextView, formatando os valores da posicao se maior ou menor que duas casas decimais
    public void changeAllTextViews() {
        changeTextView(String.valueOf(parseFloat(econ) * seekbarValue), tvEcon);
        changeTextView(String.valueOf(parseFloat(dipl) * seekbarValue), tvDipl);
        changeTextView(String.valueOf(parseFloat(govt) * seekbarValue), tvGovt);
        changeTextView(String.valueOf(parseFloat(scty) * seekbarValue), tvScty);
        changeTextView(String.valueOf(seekbarValue), textView);

        changeTextView(getQuestionsFromJSON().get(posQuestion), tvQuestions);

        if (posQuestion < 9) {
            changeTextView("Questão 0" + String.valueOf(posQuestion + 1) + " de 70", questionPosition);
        } else {
            changeTextView("Questão " + String.valueOf(posQuestion + 1) + " de 70", questionPosition);
        }
    }

    // Soma os valores escolhidos pelo user e os valores maximos de cada um dos 4 valores: econ, dipl, govt scty
    public void getUserValues() {
        ArrayList<Float> floats = new ArrayList<Float>();
        floats.addAll(Arrays.asList(parseFloat(econ) * seekbarValue, parseFloat(dipl) * seekbarValue, parseFloat(govt) * seekbarValue, parseFloat(scty) * seekbarValue));

        // Valores da escolha do user
        econScore += floats.get(0) * seekbarValue;
        diplScore += floats.get(1) * seekbarValue;
        govtScore += floats.get(2) * seekbarValue;
        sctyScore += floats.get(3) * seekbarValue;

        // Valores maximos
        maxEcon += Math.abs(parseFloat(econ));
        maxDipl += Math.abs(parseFloat(dipl));
        maxGovt += Math.abs(parseFloat(govt));
        maxScty += Math.abs(parseFloat(scty));

        Log.i("User Score", "econScore: " + String.valueOf(econScore) + " | diplScore: " + String.valueOf(diplScore) + " | govtScore: " + String.valueOf(govtScore) + " | sctyScore: " + String.valueOf(sctyScore));
        Log.i("Max Score", "MAX econ: " + String.valueOf(maxEcon) + " | MAX dipl: " + String.valueOf(maxDipl) + " | MAX govt: " + String.valueOf(maxGovt) + " | MAX scty: " + String.valueOf(maxScty));
    }

    public void changeTextView(String string, TextView textView) {
        textView.setText(string);
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

    /*
    * equality = econ
    * peace = dipl
    * liberty = govt
    * progress = scty
    * wealth    = (100 - equality)
    * might     = (100 - peace)
    * authority = (100 - liberty)
    * tradition = (100 - progress)
    */
    public void resultOfEffect(ArrayList<ArrayList<Float>> result) {
        for (int pos = 0; pos < result.size(); pos ++) {
            Log.i("Value " + String.valueOf(pos), String.valueOf(result));
        }
    }
}

