package com.example.joker.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserAreaActivity extends AppCompatActivity implements View.OnClickListener {
    Button add1, take, modify, remove, report, view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        add1 = (Button) findViewById(R.id.addstd);
        take = (Button) findViewById(R.id.take);
        modify = (Button) findViewById(R.id.modify);
        remove = (Button) findViewById(R.id.remove);
        report = (Button) findViewById(R.id.report);
        view2 = (Button) findViewById(R.id.view1);

        add1.setOnClickListener(this);
        view2.setOnClickListener(this);
        // take.setOnClickListener(this);
        // modify.setOnClickListener(this);
        // remove.setOnClickListener(this);
        // report.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addstd:
                Intent addIntent = new Intent(UserAreaActivity.this, AddActivity.class);
                UserAreaActivity.this.startActivity(addIntent);
                break;




        }
    }


}
