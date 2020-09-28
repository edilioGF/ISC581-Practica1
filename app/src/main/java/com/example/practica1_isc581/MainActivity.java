package com.example.practica1_isc581;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText textName;
    private EditText textLastName;
    private EditText BirthDate;
    private Button buttonSend;
    private Button buttonClear;
    private Spinner spinnerGender;
    private RadioButton radioBtnYes;
    private RadioButton radioBtnNo;
    private CheckBox checkJava;
    private CheckBox checkGo;
    private CheckBox checkPython;
    private CheckBox checkJS;
    private CheckBox checkC;
    private CheckBox checkCsharp;
    private String spn_opt;

    private String[] genders = {"Masculino", "Femenino"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.textName);
        textLastName = findViewById(R.id.textLastName);
        BirthDate = findViewById(R.id.DateBirth);
        buttonSend = findViewById(R.id.buttonSend);
        buttonClear = findViewById(R.id.buttonClear);
        spinnerGender = findViewById(R.id.spinnerGender);
        radioBtnYes = findViewById(R.id.radioBtnYes);
        radioBtnNo = findViewById(R.id.radioBtnNo);
        checkJava = findViewById(R.id.checkBoxJava);
        checkPython = findViewById(R.id.checkBoxPython);
        checkJS = findViewById(R.id.checkBoxJS);
        checkGo = findViewById(R.id.checkBoxGo);
        checkC = findViewById(R.id.checkBoxCc);
        checkCsharp = findViewById(R.id.checkBoxCsharp);


        disable_chbx();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        spinnerGender.setOnItemSelectedListener(this);


        radioBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBtnNo.setChecked(false);
                radioBtnYes.setChecked(true);

                checkJava.setEnabled(true);
                checkPython.setEnabled(true);
                checkJS.setEnabled(true);
                checkGo.setEnabled(true);
                checkC.setEnabled(true);
                checkCsharp.setEnabled(true);
            }
        });

        radioBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBtnYes.setChecked(false);
                radioBtnNo.setChecked(true);

                checkJava.setEnabled(false);
                checkPython.setEnabled(false);
                checkJS.setEnabled(false);
                checkGo.setEnabled(false);
                checkC.setEnabled(false);
                checkCsharp.setEnabled(false);
            }
        });


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString().trim();
                String lastName = textLastName.getText().toString().trim();
                String birthDate = BirthDate.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    textName.setError("Debe ingresar un nombre");
                    textName.setFocusable(true);
                    return;
                } else if (TextUtils.isEmpty(lastName)) {
                    textLastName.setError("Debe ingresar un apellido");
                    textLastName.setFocusable(true);
                    return;
                } else if (TextUtils.isEmpty(birthDate)) {
                    BirthDate.setError("Por favor ingrese su fecha de nacimiento");
                    BirthDate.setFocusable(true);
                    return;
                }

                if (!radioBtnNo.isChecked()) {
                    if (!radioBtnYes.isChecked()) {
                        radioBtnYes.setError("Debe seleccionar una opcion.");
                        return;
                    }
                }

                String programmer = radioBtnYes.isChecked() ? "Me gusta programar" : "No me gusta programar";

                Intent intent = new Intent(MainActivity.this, DisplayDataActivity.class);

                if (programmer == "Me gusta programar") {

                    String languages = "";

                    if (checkJava.isChecked()) {
                        languages += "Java, ";
                    }
                    if (checkPython.isChecked()) {
                        languages += "Python, ";
                    }
                    if (checkJS.isChecked()) {
                        languages += "JS, ";
                    }
                    if (checkGo.isChecked()) {
                        languages += "Go Land, ";
                    }
                    if (checkC.isChecked()) {
                        languages += "C/C++, ";
                    }
                    if (checkCsharp.isChecked()) {
                        languages += "C#, ";
                    }
                    if (languages.length() > 0) {
                        languages = languages.substring(0, languages.length() - 2);
                    }

                    intent.putExtra("name", name);
                    intent.putExtra("last_name", lastName);
                    intent.putExtra("birth_date", birthDate);
                    intent.putExtra("genre", spn_opt);
                    intent.putExtra("programmer", programmer);
                    intent.putExtra("languages", languages);

                } else {

                    intent.putExtra("name", name);
                    intent.putExtra("last_name", lastName);
                    intent.putExtra("birth_date", birthDate);
                    intent.putExtra("genre", spn_opt);
                    intent.putExtra("programmer", programmer);
                    intent.putExtra("languages", "");

                }

                MainActivity.this.startActivity(intent);
            }
        });


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.clearFields();
            }
        });
    }

    private void disable_chbx() {

        checkJava.setEnabled(false);
        checkPython.setEnabled(false);
        checkJS.setEnabled(false);
        checkGo.setEnabled(false);
        checkC.setEnabled(false);
        checkCsharp.setEnabled(false);

    }


    public void clearFields() {

        textName.setText("");
        textLastName.setText("");
        BirthDate.setText("");
        spinnerGender.setSelection(0);
        radioBtnYes.setChecked(false);
        radioBtnNo.setChecked(false);
        checkJava.setChecked(false);
        checkPython.setChecked(false);
        checkJS.setChecked(false);
        checkGo.setChecked(false);
        checkC.setChecked(false);
        checkCsharp.setChecked(false);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spn_opt = genders[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}