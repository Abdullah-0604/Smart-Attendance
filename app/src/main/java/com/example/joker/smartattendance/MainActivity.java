package com.example.joker.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private static Button button_about;
private static CardView button_getstarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
         onclickgetstarted();
         onclickabout();

        }
    }

    public void onclickgetstarted(){
        button_getstarted = (CardView) findViewById(R.id.button_getstarted);

        button_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.joker.smartattendance.GetStarted");
                startActivity(intent);
            }
        });
    }

    public void onclickabout(){

        button_about = (Button) findViewById(R.id.button_about);

        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.joker.smartattendance.About");
                startActivity(intent);

            }
        });
    }
}
