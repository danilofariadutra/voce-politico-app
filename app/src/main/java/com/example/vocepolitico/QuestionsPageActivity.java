package com.example.vocepolitico;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.DIPL;
import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.ECON;
import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.GOVT;
import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.SCTY;
import static com.example.vocepolitico.R.layout.questions_page;
import static java.lang.Float.parseFloat;

public class QuestionsPageActivity extends QuestionsPageController {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(questions_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setupAll();
        showQuestionsOnDisplay(posQuestion);
        QuestionsPageActivity.changeTextView(QuestionsPageActivity.returnMultiplyEffectText(2), tvUserChoice);

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

                changeTextView(QuestionsPageActivity.returnMultiplyEffectText(i), tvUserChoice);

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

    public void setupVisibility() {
        tvQuestions.setVisibility(View.INVISIBLE);
        questionPosition.setVisibility(View.INVISIBLE);
        seekbarEffectMultiply.setVisibility(View.INVISIBLE);
        btnQuestion.setVisibility(View.INVISIBLE);
        tvCalculatingText.setVisibility(View.VISIBLE);
        tvUserChoice.setVisibility(View.INVISIBLE);
        tvUserTitle.setVisibility(View.INVISIBLE);
    }

    public boolean avoidCrashActivity() {
        int sizeOfArrayQuestions = readJSONFile(R.raw.questions).size();
        if (posQuestion >= sizeOfArrayQuestions) {
            posQuestion = 0;
            setupVisibility();

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
            animation.reset();
            tvCalculatingText.clearAnimation();
            tvCalculatingText.startAnimation(animation);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(QuestionsPageActivity.this, QuestionResultActivity.class);
                    startActivity(intent);
                }
            }, 5000); //5000
            return true;
        }
        return false;
    }

    public ArrayList<String> readJSONFile(Integer jsonRawFile) {
        ArrayList<String> questions_list = new ArrayList<>();
        String question_string = "";
        StringBuilder question_string_builder = new StringBuilder();
        InputStream questions_input_stream = this.getResources().openRawResource(jsonRawFile);
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

    // Exibe todas as informacoes na Activity
    public void showQuestionsOnDisplay(Integer pos)  {
        setInitialSeekbarProgress();
        econ = getStatValuesFromEffect(readJSONFile(R.raw.effect), ECON);
        dipl = getStatValuesFromEffect(readJSONFile(R.raw.effect), DIPL);
        govt = getStatValuesFromEffect(readJSONFile(R.raw.effect), GOVT);
        scty = getStatValuesFromEffect(readJSONFile(R.raw.effect), SCTY);
        changeAllTextViews();
        }

    public void setInitialSeekbarProgress() {
        seekbarEffectMultiply.setProgress(2);
        if (seekbarEffectMultiply.getProgress() == 2) seekbarValue = 0f;
    }

    // Quebra os valores da ArrayList<String> com os 4 valores (econ, dipl, govt, scty), e repassa para econ, dipl, govt, scty
    public static String getStatValuesFromEffect(ArrayList<String> arrayList, ValuesEnum pos) {
        values = arrayList.get(posQuestion).replaceAll(":", "").replaceAll("'", "").replace("{", "").replace("}", "");
        String valueString = "";
        switch(pos) {
            case ECON:
                valueString = values.substring(values.indexOf("econ ") + 5, values.indexOf(","));
                break;
            case DIPL:
                valueString = values.substring(values.indexOf("dipl ") + 5, values.indexOf(",", values.indexOf("dipl ")));
                break;
            case GOVT:
                valueString = values.substring(values.indexOf("govt ") + 5, values.indexOf(",", values.indexOf("govt ")));
                break;
            case SCTY:
                valueString = values.substring(values.indexOf("scty ") + 5);
                break;
        }
                return valueString;
   }

    // Mostra os valores econ, dipl, govt e scty, multiplicando pelo valor da seekbar e mostra a questão em TextView, formatando os valores da posicao se maior ou menor que duas casas decimais
    public void changeAllTextViews() {
        changeTextView(String.valueOf(parseFloat(econ) * seekbarValue), tvEcon);
        changeTextView(String.valueOf(parseFloat(dipl) * seekbarValue), tvDipl);
        changeTextView(String.valueOf(parseFloat(govt) * seekbarValue), tvGovt);
        changeTextView(String.valueOf(parseFloat(scty) * seekbarValue), tvScty);
        changeTextView(String.valueOf(seekbarValue), textView);

        changeTextView(readJSONFile(R.raw.questions).get(posQuestion), tvQuestions);

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
        econScore += floats.get(0);
        diplScore += floats.get(1);
        govtScore += floats.get(2);
        sctyScore += floats.get(3);

        // Valores maximos
        maxEcon += Math.abs(parseFloat(econ));
        maxDipl += Math.abs(parseFloat(dipl));
        maxGovt += Math.abs(parseFloat(govt));
        maxScty += Math.abs(parseFloat(scty));

        Log.i("User Score", "econScore: " + String.valueOf(econScore) + " | diplScore: " + String.valueOf(diplScore) + " | govtScore: " + String.valueOf(govtScore) + " | sctyScore: " + String.valueOf(sctyScore));
        Log.i("Max Score", "MAX econ: " + String.valueOf(maxEcon) + " | MAX dipl: " + String.valueOf(maxDipl) + " | MAX govt: " + String.valueOf(maxGovt) + " | MAX scty: " + String.valueOf(maxScty));
        userScore.setText(String.valueOf(econScore) + " " + String.valueOf(diplScore) + " " + String.valueOf(govtScore) + " " + String.valueOf(sctyScore));
        maxScore.setText(String.valueOf(maxEcon) + " " + String.valueOf(maxDipl) + " " + String.valueOf(maxGovt) + " " + String.valueOf(maxScty));
    }

    public static void changeTextView(String string, TextView textView) {
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

    public static String returnMultiplyEffectText(Integer seekbar_value) {
        switch (seekbar_value) {
            case 0:
                userOpinion = "Discordo Fortemente";
                break;
            case 1:
                userOpinion = "Discordo";
                break;
            case 2:
                userOpinion = "Neutro";
                break;
            case 3:
                userOpinion = "Concordo";
                break;
            case 4:
                userOpinion = "Concordo Fortemente";
                break;
            default:
                userOpinion = "Neutro";
                break;
        }
        return userOpinion;
    }

    public void getEffectResult(ArrayList<ArrayList<Float>> result) {
        for (int pos = 0; pos < result.size(); pos ++) {
            Log.i("Value " + String.valueOf(pos), String.valueOf(result));
        }
    }

    public void resultOfEffect(ArrayList<ArrayList<Float>> result) {
        for (int pos = 0; pos < result.size(); pos ++) {
            Log.i("Value " + String.valueOf(pos), String.valueOf(result));
        }
    }
}

