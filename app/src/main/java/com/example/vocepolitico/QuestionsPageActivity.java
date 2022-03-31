package com.example.vocepolitico;

import android.content.Context;
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
//                String jsonFileString = readJson(getApplicationContext(), "questions.json");
            }
        });
    }

    public void ReadTextFile()  {
        ArrayList<String> effect_list = new ArrayList<>();
        String effect_string = "";
        StringBuilder effect_string_builder = new StringBuilder();
        InputStream effect_input_stream = this.getResources().openRawResource(R.raw.effect);
        BufferedReader effect_values_buffer_reader = new BufferedReader(new InputStreamReader(effect_input_stream));

        ArrayList<String> questions_list = new ArrayList<>();
        String question_string = "";
        StringBuilder question_string_builder = new StringBuilder();
        InputStream questions_input_stream = this.getResources().openRawResource(R.raw.questions);
        BufferedReader questions_buffer_reader = new BufferedReader(new InputStreamReader(questions_input_stream));


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



        if (pos == effect_list.size()) pos = 0;

//        if (String.valueOf(questions.get(pos)).contains("econ")) {
        String values = effect_list.get(pos).replaceAll(":", "").replaceAll("'", "").replace("{", "").replace("}", "");
        String econ = values.substring(values.indexOf("econ ") + 5, values.indexOf(","));
        String dipl = values.substring(values.indexOf("dipl ") + 5, values.indexOf(",", values.indexOf("dipl ")));
        String govt = values.substring(values.indexOf("govt ") + 5, values.indexOf(",", values.indexOf("govt ")));
        String scty = values.substring(values.indexOf("scty ") + 5);

//        Integer econ_int = Integer.parseInt(econ);
//        }

        tv_questions.setText(" Question " + String.valueOf(pos + 1) + ": \n" + String.valueOf(questions_list.get(pos)));
        tv_econ.setText(econ);
        tv_dipl.setText(dipl);
        tv_govt.setText(govt);
        tv_scty.setText(scty);

        pos++;
    }

    public String readJson(Context context, String filename) {
        String jsonString = "";

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}

