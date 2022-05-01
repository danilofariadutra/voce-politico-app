package com.example.vocepolitico;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

//import static com.example.vocepolitico.QuestionResultController.string;

public class NavigationHomePage extends Fragment {
//    public static TextView[] linksTextViewList;
    public static ArrayList<Integer> arrayList;
    public static TextView link1, link2, link3, link4, link5, link6, link7;
    public String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_home, container, false);

        setupItems(view);

        return view;
    }

    public static NavigationHomePage newInstance() {
        return new NavigationHomePage();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void setupItems(View view) {
        TextView[] linksTextViewList = new TextView[7];
        linksTextViewList = new TextView[]{link1, link2, link3, link4, link5, link6, link7};

        arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(R.id.link_01, R.id.link_02, R.id.link_03, R.id.link_04, R.id.link_05, R.id.link_06, R.id.link_07));

        for(int itemPos = 0; itemPos < linksTextViewList.length; itemPos ++) {
            int finalItemPos = itemPos;
            linksTextViewList[itemPos] = view.findViewById(arrayList.get(itemPos));
            linksTextViewList[itemPos].setText(readJSONFile(R.raw.url_title).get(itemPos));

            linksTextViewList[itemPos].setClickable(true);

            linksTextViewList[itemPos].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    url = readJSONFile(R.raw.url_string).get(finalItemPos);

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
        }
    }
}