package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class AddDelavecActivity extends AppCompatActivity {
    private TextView status;
    private EditText name;
    private EditText surname;
    private RequestQueue requestQueue;
    private String url = "https://knjigovodstvo.azurewebsites.net/api/v1/delavci";

    public void addDelavec(View view){
        if (view != null){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delavec);
        name = (EditText) findViewById(R.id.ime);
        surname = (EditText) findViewById(R.id.priimek);
        status = (TextView) findViewById(R.id.stanje);
    }
}