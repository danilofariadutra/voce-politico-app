package com.example.vocepolitico;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.vocepolitico.R.layout.test_sharedpreferences;

public class test_sharedpreferences extends AppCompatActivity {
    public static TextView textView;
    public static EditText editText;
    public static Button button;
    public static String string;
    public static String textview_string;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(test_sharedpreferences);

        textView = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);

        sharedPreferences = getSharedPreferences("PREFERENCIAS", MODE_PRIVATE);

        textview_string = sharedPreferences.getString("textsaved", "");

        // Se algum dado já estiver gravado
//        if (textview_string != "") Toast.makeText(this, textview_string, Toast.LENGTH_SHORT).show();
        if (textview_string != "") {
            textView.setText(textview_string);
            Toast.makeText(this, textview_string, Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string = editText.getText().toString();
                editor = sharedPreferences.edit();
                editor.putString("textsaved", string);
                editor.commit();


                if (sharedPreferences.edit().commit())
                    Toast.makeText(test_sharedpreferences.this, "Dados gravados com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
