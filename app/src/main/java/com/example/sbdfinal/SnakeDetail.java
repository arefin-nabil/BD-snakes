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

import java.util.HashMap;

public class SnakeDetail extends AppCompatActivity {

    TextView textView, textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_detail);


        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);


        // Retrieve the color passed via Intent
        String color = getIntent().getStringExtra("bgColor");
        if (color != null) {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(color));
        }

        String snakebangname = getIntent().getStringExtra("snakebangname");
        String snakeengname = getIntent().getStringExtra("snakeengname");
        String snakesciname = getIntent().getStringExtra("snakesciname");
        String text1 = getIntent().getStringExtra("text1");
        String text2 = getIntent().getStringExtra("text2");
        String text3 = getIntent().getStringExtra("text3");

        textView.setText(text1);
        textView1.setText(text2);
        textView2.setText(text3);


    }


}