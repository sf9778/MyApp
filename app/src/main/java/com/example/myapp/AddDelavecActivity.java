package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class AddDelavecActivity extends AppCompatActivity {

    private TextView status;
    private EditText name;
    private EditText surname;
    private EditText id;
    private RequestQueue requestQueue;
    private final String url = "https://knjigovodstvo.azurewebsites.net/api/v1/delavci";
    private String text;
    private String value;
    public void addDelavec(View view){
        status.setText("Objavljanje na: "+url);

        try {
            JSONObject jsonBody = new JSONObject();
            text = name.getText().toString();
            value = text.substring(4);
            jsonBody.put("ime",value);
            text = surname.getText().toString();
            value = text.substring(8);
            jsonBody.put("priimek",value);
            text = id.getText().toString();
            value = text.substring(3);
            jsonBody.put("id",value);
            final String requestBody = jsonBody.toString();


            status.setText(requestBody);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                }
            }
            ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        status.setText(responseString);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

            };


            requestQueue.add(stringRequest);
            //status.setText(stringRequest.toString());
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
        status = (TextView) findViewById(R.id.status);
        id = (EditText) findViewById(R.id.delavecID);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }
}