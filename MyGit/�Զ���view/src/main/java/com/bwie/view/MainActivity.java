package com.bwie.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyCircleView my_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_view = (MyCircleView) findViewById(R.id.my_view);

    }
    public void onClick(View view){
        my_view.setColor(Color.BLACK);
    }
    public void add(View view){
        my_view.speed();
    }
    public void slow(View view){
        my_view.slowDown();
    }
}
