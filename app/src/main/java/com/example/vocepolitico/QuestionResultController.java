package com.example.vocepolitico;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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


    public static List<String> econArray = Arrays.asList("Communist", "Socialist", "Social", "Centrist", "Market", "Capitalist", "Laissez-Faire");
    public static List<String> diplArray =  Arrays.asList("Cosmopolitan", "Internationalist", "Peaceful", "Balanced", "Patriotic", "Nationalist", "Chauvinist");
    public static List<String> govtArray = Arrays.asList("Anarchist", "Libertarian", "Liberal", "Moderate", "Statist", "Authoritarian", "Totalitarian");
    public static List<String> sctyArray = Arrays.asList("Revolutionary", "Very Progressive", "Progressive", "Neutral", "Traditional", "Very Traditional", "Reactionary");

    public static List<List> items = Arrays.asList(econArray, diplArray, govtArray, sctyArray);

    public static int counter = 0;

    public void setupItems() {
        tvResult = findViewById(R.id.result_title_textview);
        tvResultValue = findViewById(R.id.result_value_textview);
        tvEconStatsIdeology = findViewById(R.id.economic_axis_value_textview);
        tvDiplStatsIdeology = findViewById(R.id.diplomatic_axis_value_textview);
        tvGovtStatsIdeology = findViewById(R.id.civil_axis_value_textview);
        tvSctyStatsIdeology = findViewById(R.id.societal_axis_value_textview);

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
        Log.i("Bar Values", String.valueOf(economicBarValue) + " | " + String.valueOf(diplomaticBarValue) + " | " + String.valueOf(civilBarValue) + " | " + String.valueOf(societalBarValue) + " | ");

        economicProgressBar = findViewById(R.id.economic_progressbar);
        economicProgressBar.setProgress(Math.round(economicBarValue));
        Log.i("Economic Bar Value", String.valueOf(Math.round(economicBarValue)));

        diplomaticProgressBar = findViewById(R.id.diplomatic_progressbar);
        diplomaticProgressBar.setProgress(Math.round(diplomaticBarValue));
        Log.i("Diplomatic Bar Value", String.valueOf(Math.round(diplomaticBarValue)));

        civilProgressBar = findViewById(R.id.civil_progressbar);
        civilProgressBar.setProgress(Math.round(civilBarValue));
        Log.i("Civil Bar Value", String.valueOf(Math.round(civilBarValue)));

        societalProgressBar = findViewById(R.id.societal_progressbar);
        societalProgressBar.setProgress(Math.round(societalBarValue));
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
}
