package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static com.example.vocepolitico.QuestionResultController.civilBarValue;
import static com.example.vocepolitico.QuestionResultController.diplomaticBarValue;
import static com.example.vocepolitico.QuestionResultController.economicBarValue;
import static com.example.vocepolitico.QuestionResultController.ideologiesDiplStats;
import static com.example.vocepolitico.QuestionResultController.ideologiesEconStats;
import static com.example.vocepolitico.QuestionResultController.ideologiesGovtStats;
import static com.example.vocepolitico.QuestionResultController.ideologiesSctyStats;
import static com.example.vocepolitico.QuestionResultController.societalBarValue;
import static com.example.vocepolitico.QuestionResultController.strShPrefDiplBarValue;
import static com.example.vocepolitico.QuestionResultController.strShPrefDiplStats;
import static com.example.vocepolitico.QuestionResultController.strShPrefEconStats;
import static com.example.vocepolitico.QuestionResultController.strShPrefGovtBarValue;
import static com.example.vocepolitico.QuestionResultController.strShPrefGovtStats;
import static com.example.vocepolitico.QuestionResultController.strShPrefIEconBarValue;
import static com.example.vocepolitico.QuestionResultController.strShPrefIdeology;
import static com.example.vocepolitico.QuestionResultController.strShPrefSctyBarValue;
import static com.example.vocepolitico.QuestionResultController.strShPrefSctyStats;
import static com.example.vocepolitico.QuestionsPageController.diplScore;
import static com.example.vocepolitico.QuestionsPageController.econScore;
import static com.example.vocepolitico.QuestionsPageController.govtScore;
import static com.example.vocepolitico.QuestionsPageController.maxDipl;
import static com.example.vocepolitico.QuestionsPageController.maxEcon;
import static com.example.vocepolitico.QuestionsPageController.maxGovt;
import static com.example.vocepolitico.QuestionsPageController.maxScty;
import static com.example.vocepolitico.QuestionsPageController.posQuestion;
import static com.example.vocepolitico.QuestionsPageController.sctyScore;

public class NavigationInfoPage extends Fragment {
    private Button btnRestartTest;
    private TextView textViewIdeology;
    private TextView textViewEconomicStats;
    private TextView textViewDiplomaticStats;
    private TextView textViewCivilStats;
    private TextView textViewSocietalStats;
    private ProgressBar econProgressBar;
    private ProgressBar diplProgressBar;
    private ProgressBar govtProgressBar;
    private ProgressBar sctyProgressBar;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.navigation_info, container, false);

        setupItems();

        btnRestartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTest();
            }
        });
        return view;
    }

    private void setupItems() {
        textViewIdeology = view.findViewById(R.id.navigation_value_textview);
        textViewEconomicStats = view.findViewById(R.id.navigation_economic_axis_value_textview);
        textViewDiplomaticStats = view.findViewById(R.id.navigation_diplomatic_axis_value_textview);
        textViewCivilStats = view.findViewById(R.id.navigation_civil_axis_value_textview);
        textViewSocietalStats = view.findViewById(R.id.navigation_societal_axis_value_textview);

        econProgressBar = view.findViewById(R.id.navigation_economic_progressbar);
        diplProgressBar = view.findViewById(R.id.navigation_diplomatic_progressbar);
        govtProgressBar = view.findViewById(R.id.navigation_civil_progressbar);
        sctyProgressBar = view.findViewById(R.id.navigation_societal_progressbar);

        btnRestartTest = view.findViewById(R.id.btn_restart_test);

        econProgressBar.setProgress(Math.round(Float.parseFloat(strShPrefIEconBarValue)));
        diplProgressBar.setProgress(Math.round(Float.parseFloat(strShPrefDiplBarValue)));
        govtProgressBar.setProgress(Math.round(Float.parseFloat(strShPrefGovtBarValue)));
        sctyProgressBar.setProgress(Math.round(Float.parseFloat(strShPrefSctyBarValue)));

        textViewIdeology.setText(strShPrefIdeology);
        textViewEconomicStats.setText(strShPrefEconStats);
        textViewDiplomaticStats.setText(strShPrefDiplStats);
        textViewCivilStats.setText(strShPrefGovtStats);
        textViewSocietalStats.setText(strShPrefSctyStats);
    }

    private void resetTest() {
        posQuestion = 0;
        economicBarValue = 0f;
        diplomaticBarValue = 0f;
        civilBarValue = 0f;
        societalBarValue = 0f;

        ideologiesEconStats = 0;
        ideologiesDiplStats = 0;
        ideologiesGovtStats = 0;
        ideologiesSctyStats = 0;

        maxEcon = 0f;
        maxDipl = 0f;
        maxGovt = 0f;
        maxScty = 0f;

        econScore = 0f;
        diplScore = 0f;
        sctyScore = 0f;
        govtScore = 0f;

        Intent intent = new Intent(getContext(), EightValuesExplanationOne.class);
        startActivity(intent);
    }

    public static NavigationInfoPage newInstance() {
        return new NavigationInfoPage();
    }
}