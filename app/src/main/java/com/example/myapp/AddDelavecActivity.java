package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

public class AddDelavecActivity extends AppCompatActivity {
    private TextView status;
    private EditText name;
    private EditText surname;
    private RequestQueue requestQueue;
    private String url = "https://knjigovodstvo.azurewebsites.net/api/v1/delavci";
    public void addDelavec(View view){
        try {
            this.status.setText("Objavljanje na: "+url);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("first_name",name);
            jsonBody.put("last_name",surname);
        } catch(JSONException e){
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
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