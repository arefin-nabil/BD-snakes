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

    TextView textView, textView1, textView2, textView3, textView4, textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_detail);


        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);


        // Retrieve the color passed via Intent
        String color = getIntent().getStringExtra("bgColor");
        if (color != null) {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(color));
        }

        String snakebangname = getIntent().getStringExtra("snakebangname");
        String snakeengname = getIntent().getStringExtra("snakeengname");
        String snakesciname = getIntent().getStringExtra("snakesciname");
        String identity = getIntent().getStringExtra("identity");
        String detail = getIntent().getStringExtra("detail");
        String ending = getIntent().getStringExtra("ending");


        textView.setText(snakebangname);
        textView1.setText(snakeengname);
        textView2.setText(snakesciname);
        textView3.setText(identity);
        textView4.setText(detail);
        textView5.setText(ending);


    }


}