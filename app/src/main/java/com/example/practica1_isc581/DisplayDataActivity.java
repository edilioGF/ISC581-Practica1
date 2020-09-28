package com.example.practica1_isc581;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayDataActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        textView = findViewById(R.id.textMessage);

        Intent intent  = getIntent();
        String name = intent.getStringExtra("name");
        String last_name = intent.getStringExtra("last_name");
        String bdate = intent.getStringExtra("birth_date");
        String genero = intent.getStringExtra("genre");
        String prog = intent.getStringExtra("programmer");
        String lang = intent.getStringExtra("languages");

        String text = "Hola! mi nombre es : " + name +" "+ last_name + "\n\nSoy " + genero + " y naci en fecha " + bdate + ".\n\n" + prog + ".";
        if (!lang.isEmpty()) {
            text += " Mis lenguajes favoritos son: " + lang;
        }

        textView.setText(text);
    }
}