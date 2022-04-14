package com.example.vocepolitico;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class QuestionResultActivity extends QuestionResultController {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        setupItems();

        resultTextAnimation();
    }
}
