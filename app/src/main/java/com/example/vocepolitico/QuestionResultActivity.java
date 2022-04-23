package com.example.vocepolitico;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;

import androidx.annotation.Nullable;

public class QuestionResultActivity extends QuestionResultController {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setupTransitions();
        setupItems();

        resultTextAnimation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    public void clearPreviouslyAnimation() {
        tvResult.clearAnimation();
        tvResult.setVisibility(View.INVISIBLE);
        economicProgressBar.setVisibility(View.INVISIBLE);
        diplomaticProgressBar.setVisibility(View.INVISIBLE);
        civilProgressBar.setVisibility(View.INVISIBLE);
        societalProgressBar.setVisibility(View.INVISIBLE);
        btnAboutResult.setVisibility(View.INVISIBLE);
        tvEconStatsIdeology.setVisibility(View.INVISIBLE);
        tvDiplStatsIdeology.setVisibility(View.INVISIBLE);
        tvGovtStatsIdeology.setVisibility(View.INVISIBLE);
        tvSctyStatsIdeology.setVisibility(View.INVISIBLE);
        tvEconTitle.setVisibility(View.INVISIBLE);
        tvDiplTitle.setVisibility(View.INVISIBLE);
        tvGovtTitle.setVisibility(View.INVISIBLE);
        tvSctyTitle.setVisibility(View.INVISIBLE);
        tvResultSubtitle.setVisibility(View.INVISIBLE);
        imgvSocialism.setVisibility(View.INVISIBLE);
        imgvLiberalism.setVisibility(View.INVISIBLE);
        imgvNacionalism.setVisibility(View.INVISIBLE);
        imgvGlobalism.setVisibility(View.INVISIBLE);
    }

    public void resultToAboutOne(View view) {
        clearPreviouslyAnimation();
        Transition slide = new Fade();
        slide.setDuration(1300);

        TransitionManager.go(scSplashAboutOne, slide);
    }

    public void resultToAboutTwo(View view) {
        clearPreviouslyAnimation();
        Transition slide = new Fade();
        slide.setDuration(1300);

        TransitionManager.go(scSplashAboutTwo, slide);
    }

    public void aboutTwoToMenuActivity(View view) {
        clearPreviouslyAnimation();
        Transition slide = new Fade();
        slide.setDuration(1300);

        TransitionManager.go(scSplashAboutToMenu, slide);
    }
}
