package com.example.menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class PrefActivity extends AppCompatActivity {
Spinner spinner;
Spinner spinner2;
Button button;
String colourSelected = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.colourList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        colourSelected = "Black";
                        SaveIntoSharedPrefs("colour",colourSelected);
                        break;
                    case 2:
                        colourSelected = "Grey";
                        SaveIntoSharedPrefs("colour",colourSelected);
                        break;
                    case 3:
                        colourSelected = "White";
                        SaveIntoSharedPrefs("colour",colourSelected);

                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.colourList));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        colourSelected = "Black";
                        SaveIntoSharedPrefs("colourC",colourSelected);
                        break;
                    case 2:
                        colourSelected = "Grey";
                        SaveIntoSharedPrefs("colourC",colourSelected);
                        break;
                    case 3:
                        colourSelected = "White";
                        SaveIntoSharedPrefs("colourC",colourSelected);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        }

    private void SaveIntoSharedPrefs(String key, String value) {
        SharedPreferences prefs=getSharedPreferences("bgColour", MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,value);
        editor.apply();
    }

}