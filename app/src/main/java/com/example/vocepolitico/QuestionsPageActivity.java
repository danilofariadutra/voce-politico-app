package com.example.vocepolitico;

import android.os.Bundle;
import android.view.View;

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
                ReadTextFile();
            }
        });
    }

    public void ReadTextFile()  {
        ArrayList<String> questions = new ArrayList<>();
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = this.getResources().openRawResource(R.raw.questions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(string);
            if (string != "\n") {
                questions.add(string);
                string = "";
            }
        }

        if (pos == questions.size()) pos = 0;

        tv_questions.setText(" Question " + String.valueOf(pos + 1) + ": \n" + String.valueOf(questions.get(pos)));
        pos ++;
    }
}
