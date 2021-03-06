package com.example.vocepolitico;

import android.content.Intent;
import android.content.SharedPreferences;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.vocepolitico.QuestionsPageActivity.changeTextView;
import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.DIPL;
import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.ECON;
import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.GOVT;
import static com.example.vocepolitico.QuestionsPageController.ValuesEnum.SCTY;
import static com.example.vocepolitico.QuestionsPageController.diplScore;
import static com.example.vocepolitico.QuestionsPageController.econScore;
import static com.example.vocepolitico.QuestionsPageController.govtScore;
import static com.example.vocepolitico.QuestionsPageController.maxDipl;
import static com.example.vocepolitico.QuestionsPageController.maxEcon;
import static com.example.vocepolitico.QuestionsPageController.maxGovt;
import static com.example.vocepolitico.QuestionsPageController.maxScty;
import static com.example.vocepolitico.QuestionsPageController.posQuestion;
import static com.example.vocepolitico.QuestionsPageController.sctyScore;

public class QuestionResultController extends AppCompatActivity {
    public static TextView tvResult;
    public static TextView tvResultValue;
    public static TextView tvEconStatsIdeology;
    public static TextView tvDiplStatsIdeology;
    public static TextView tvGovtStatsIdeology;
    public static TextView tvSctyStatsIdeology;
    public static TextView tvEconTitle;
    public static TextView tvDiplTitle;
    public static TextView tvGovtTitle;
    public static TextView tvSctyTitle;
    public static TextView tvResultSubtitle;

    public static ImageView imgvSocialism;
    public static ImageView imgvLiberalism;
    public static ImageView imgvNacionalism;
    public static ImageView imgvGlobalism;

    public Button btnAboutResult;

    public static ProgressBar economicProgressBar;
    public static ProgressBar diplomaticProgressBar;
    public static ProgressBar civilProgressBar;
    public static ProgressBar societalProgressBar;

    public static Float economicBarValue;
    public static Float diplomaticBarValue;
    public static Float civilBarValue;
    public static Float societalBarValue;

    public static Integer ideologiesEconStats;
    public static Integer ideologiesDiplStats;
    public static Integer ideologiesGovtStats;
    public static Integer ideologiesSctyStats;

    public static List<String> econArray = Arrays.asList("Comunista", "Socialista", "Social-Democrata", "Centrista", "Liberal", "Capitalista", "Laissez-Faire");
    public static List<String> diplArray =  Arrays.asList("Cosmopolitano", "Internationalista", "Pacifista", "Moderado", "Patriota", "Nationalista", "Extremista");
    public static List<String> govtArray = Arrays.asList("Anarquista", "Libert??rio", "Liberal", "Moderado", "Estatista", "Autorit??rio", "Totalit??rio");
    public static List<String> sctyArray = Arrays.asList("Revolucion??rio", "Progressista Extremo", "Progressista", "Neutro", "Conservador", "Conservador Extremo", "Reacion??rio");

    public static SharedPreferences shprefIdeology,
                                    shprefEconStats,
                                    shprefDiplStats,
                                    shprefGovtStats,
                                    shprefSctyStats;

    public static SharedPreferences shprefEconBarValue,
                                    shprefDiplBarValue,
                                    shprefGovtBarValue,
                                    shprefSctyBarValue;

    public static SharedPreferences.Editor editor;

    public static String strShPrefIdeology,
                         strShPrefEconStats,
                         strShPrefDiplStats,
                         strShPrefGovtStats,
                         strShPrefSctyStats;

    public static String strShPrefIEconBarValue,
                         strShPrefDiplBarValue,
                         strShPrefGovtBarValue,
                         strShPrefSctyBarValue;

    public void setupItems() {
        tvResult = findViewById(R.id.result_title_textview);
        tvResultValue = findViewById(R.id.result_value_textview);
        tvEconStatsIdeology = findViewById(R.id.economic_axis_value_textview);
        tvDiplStatsIdeology = findViewById(R.id.diplomatic_axis_value_textview);
        tvGovtStatsIdeology = findViewById(R.id.civil_axis_value_textview);
        tvSctyStatsIdeology = findViewById(R.id.societal_axis_value_textview);
        btnAboutResult = findViewById(R.id.btn_about_result);

        imgvSocialism = findViewById(R.id.equality_image);
        imgvLiberalism = findViewById(R.id.markets_image);
        imgvNacionalism = findViewById(R.id.nation_image);
        imgvGlobalism = findViewById(R.id.globe_image);

        tvEconTitle = findViewById(R.id.economic_axis_textview);
        tvDiplTitle = findViewById(R.id.diplomatic_axis_textview);
        tvGovtTitle = findViewById(R.id.civil_axis_textview);
        tvSctyTitle = findViewById(R.id.societal_axis_textview);
        tvResultSubtitle = findViewById(R.id.result_subtitle_textview);

        setProgressBar();
        calculateStandartIdeology();

        changeTextView(calculateStandartIdeology(), tvResultValue);
        changeTextView(calculateStatsIdeology(Math.round(economicBarValue), econArray), tvEconStatsIdeology);
        changeTextView(calculateStatsIdeology(Math.round(diplomaticBarValue), diplArray), tvDiplStatsIdeology);
        changeTextView(calculateStatsIdeology(Math.round(civilBarValue), govtArray), tvGovtStatsIdeology);
        changeTextView(calculateStatsIdeology(Math.round(societalBarValue), sctyArray), tvSctyStatsIdeology);
    }

    public String calculateStatsIdeology(Integer stats, List<String> stringList) {
        List<Integer> ordinateValues = Arrays.asList(100, 90, 75, 60, 40, 25, 10, 0);

        if(stats > ordinateValues.get(0)) {
            return "";
        } else if (stats > ordinateValues.get(1)) {
            return stringList.get(0);
        } else if (stats > ordinateValues.get(2)) {
            return stringList.get(1);
        } else if (stats > ordinateValues.get(3)) {
            return stringList.get(2);
        } else if (stats >= ordinateValues.get(4)) {
            return stringList.get(3);
        } else if (stats >= ordinateValues.get(5)) {
            return stringList.get(4);
        } else if (stats >= ordinateValues.get(6)) {
            return stringList.get(5);
        } else if (stats >= ordinateValues.get(7)) {
            return stringList.get(6);
        } else {
            return "";
        }
    }

    public String calculateStandartIdeology() {
        int ideologiesSize = readJSONFile(R.raw.ideologies_string).size();

        String ideology = "";
        double ideodist = 999f;
        double dist = 0f;

        for(posQuestion = 0; posQuestion < ideologiesSize; posQuestion ++) {
            ArrayList<String> ideologiesStats = readJSONFile(R.raw.ideologies_values);
            ArrayList<String> ideologies = readJSONFile(R.raw.ideologies_string);

            ideologiesEconStats = Integer.parseInt(QuestionsPageActivity.getStatValuesFromEffect(ideologiesStats,ECON));
            ideologiesDiplStats = Integer.parseInt(QuestionsPageActivity.getStatValuesFromEffect(ideologiesStats,DIPL));
            ideologiesGovtStats = Integer.parseInt(QuestionsPageActivity.getStatValuesFromEffect(ideologiesStats,GOVT));
            ideologiesSctyStats = Integer.parseInt(QuestionsPageActivity.getStatValuesFromEffect(ideologiesStats,SCTY));
            // SHARED PREFERENCES AQUI Para os valores dos Eixos:


            Log.i("Stats", String.valueOf(ideologiesEconStats) + String.valueOf(ideologiesDiplStats) + String.valueOf(ideologiesGovtStats) + String.valueOf(ideologiesSctyStats));
            dist = 0f;
            dist += Math.pow(Math.abs(ideologiesEconStats - economicBarValue), 2);
            dist += Math.pow(Math.abs(ideologiesDiplStats - diplomaticBarValue), 2);
            dist += Math.pow(Math.abs(ideologiesGovtStats - civilBarValue), 1.73856063);
            dist += Math.pow(Math.abs(ideologiesSctyStats - societalBarValue), 1.73856063);
            Log.i("dist4", String.valueOf(dist) + " | ideodist: " + String.valueOf(ideodist));

            if(dist < ideodist) {
                ideology = ideologies.get(posQuestion);
                ideodist = dist;
                Log.i("Ideology", ideology);
                Log.i("ideodist", String.valueOf(ideodist));
            }
        }

        return ideology;
    }


    public void getSharedPreferencesValues() {
        strShPrefIdeology = recordShardPreferencesValues(calculateStandartIdeology(), shprefIdeology, editor);
        strShPrefEconStats = recordShardPreferencesValues(calculateStatsIdeology(Math.round(economicBarValue), econArray), shprefEconStats, editor);
        strShPrefDiplStats = recordShardPreferencesValues(calculateStatsIdeology(Math.round(diplomaticBarValue), diplArray), shprefEconStats, editor);
        strShPrefGovtStats = recordShardPreferencesValues(calculateStatsIdeology(Math.round(civilBarValue), govtArray), shprefEconStats, editor);
        strShPrefSctyStats = recordShardPreferencesValues(calculateStatsIdeology(Math.round(societalBarValue), sctyArray), shprefEconStats, editor);

        strShPrefIEconBarValue = recordShardPreferencesValues(String.valueOf(calculateResultsFromUserChoice(econScore, maxEcon)), shprefEconBarValue, editor);
        strShPrefDiplBarValue = recordShardPreferencesValues(String.valueOf(calculateResultsFromUserChoice(diplScore, maxDipl)), shprefDiplBarValue, editor);
        strShPrefGovtBarValue = recordShardPreferencesValues(String.valueOf(calculateResultsFromUserChoice(govtScore, maxGovt)), shprefGovtBarValue, editor);
        strShPrefSctyBarValue = recordShardPreferencesValues(String.valueOf(calculateResultsFromUserChoice(sctyScore, maxScty)), shprefSctyBarValue, editor);
    }

    public void configSharedPreferences() {
        shprefIdeology = getSharedPreferences("VALUES", MODE_PRIVATE);

        shprefEconStats = getSharedPreferences("VALUES", MODE_PRIVATE);
        shprefDiplStats = getSharedPreferences("VALUES", MODE_PRIVATE);
        shprefGovtStats = getSharedPreferences("VALUES", MODE_PRIVATE);
        shprefSctyStats = getSharedPreferences("VALUES", MODE_PRIVATE);

        shprefEconBarValue = getSharedPreferences("VALUES", MODE_PRIVATE);
        shprefDiplBarValue = getSharedPreferences("VALUES", MODE_PRIVATE);
        shprefGovtBarValue = getSharedPreferences("VALUES", MODE_PRIVATE);
        shprefSctyBarValue = getSharedPreferences("VALUES", MODE_PRIVATE);
    }

    public String recordShardPreferencesValues(String value, SharedPreferences shpref, SharedPreferences.Editor editorPref) {
        String str = value;
        editorPref = shpref.edit();
        editorPref.putString("textsaved", str);
        editorPref.commit();

        return str;
    }

    public void testPrintList(List<List> listOfList) {
        for(int listPos = 0; listPos < listOfList.size(); listPos ++) {
            for(int listItemPos = 0; listItemPos < listOfList.get(listPos).size(); listItemPos ++) {
                Log.i("List Item", listPos + " " + listOfList.get(listPos).get(listItemPos));
            }
        }
    }

    public void setProgressBar() {
        economicBarValue = calculateResultsFromUserChoice(econScore, maxEcon);
        diplomaticBarValue = calculateResultsFromUserChoice(diplScore, maxDipl);
        civilBarValue = calculateResultsFromUserChoice(govtScore, maxGovt);
        societalBarValue = calculateResultsFromUserChoice(sctyScore, maxScty);
        // SHARED PREFERENCES AQUI Para os valores dos Eixos:

        Log.i("Bar Values", String.valueOf(economicBarValue) + " | " + String.valueOf(diplomaticBarValue) + " | " + String.valueOf(civilBarValue) + " | " + String.valueOf(societalBarValue) + " | ");

        economicProgressBar = findViewById(R.id.economic_progressbar);
        economicProgressBar.setProgress(Math.round(economicBarValue));
        Log.i("Economic Bar Value", String.valueOf(Math.round(economicBarValue)));

        diplomaticProgressBar = findViewById(R.id.diplomatic_progressbar);
        diplomaticProgressBar.setProgress(Math.round(diplomaticBarValue));
        Log.i("Diplomatic Bar Value", String.valueOf(Math.round(diplomaticBarValue)));

        civilProgressBar = findViewById(R.id.civil_progressbar);
        civilProgressBar.setProgress(Math.round(civilBarValue));
        // SHARED PREFERENCES AQUI
        Log.i("Civil Bar Value", String.valueOf(Math.round(civilBarValue)));

        societalProgressBar = findViewById(R.id.societal_progressbar);
        societalProgressBar.setProgress(Math.round(societalBarValue));
        // SHARED PREFERENCES AQUI
        Log.i("Societal Bar Value", String.valueOf(Math.round(societalBarValue)));

//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                counter ++;
//                progressBar.setProgress(counter);
//
//                if (counter  == 100) {
//                    timer.cancel();
//                }
//            }
//        };
//
//        timer.schedule(timerTask, 100, 10);

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

    public float calculateResultsFromUserChoice(Float userValue, Float maxValue) {
        return (100 * (maxValue + userValue) / (2 * maxValue));
    }

    public void resultTextAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        animation.reset();

        tvResult.clearAnimation();
        tvResult.startAnimation(animation);
        animation.setDuration(2500);
        animation.cancel();
    }

    public Scene scSplashAboutOne, scSplashAboutTwo, scSplashAboutToMenu = null;

    public void setupTransitions() {
        ViewGroup root = findViewById(R.id.sc_result_root);

        // Cria os objetos do tipo Scene
        scSplashAboutOne = Scene.getSceneForLayout(root, R.layout.result_about_one, this);
        scSplashAboutTwo = Scene.getSceneForLayout(root, R.layout.result_about_two, this);
        scSplashAboutToMenu = Scene.getSceneForLayout(root, R.layout.activity_main, this);
    }

    public void goToMenu(View view) {
        Transition transition;
        transition = new Fade();
        transition.setDuration(2000);
        transition.setInterpolator(new AnticipateInterpolator());
        TransitionManager.go(scSplashAboutToMenu, transition);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
