package com.example.sbdfinal;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SnakeDetail extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_detail);


        textView = findViewById(R.id.textView);


        // Retrieve the color passed via Intent
        String color = getIntent().getStringExtra("bgColor");
        if (color != null) {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(color));
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.56.1/Apps/data.php";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });


        queue.add(stringRequest);



    }


}