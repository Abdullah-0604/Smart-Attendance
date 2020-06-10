package com.example.joker.smartattendance;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    Button add, Final, view0;
    EditText naam, roll, mail, phon;
    DatabaseHelper myDb;

    //ListView simpleview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDb = new DatabaseHelper(this);

        add = (Button) findViewById(R.id.addinfo);
        Final = (Button) findViewById(R.id.finish);
        view0 = (Button) findViewById(R.id.viewdata);
        naam = (EditText) findViewById(R.id.name);
        roll = (EditText) findViewById(R.id.rollno);
        mail = (EditText) findViewById(R.id.email);
        phon = (EditText) findViewById(R.id.phone);
        // simpleview=(ListView) findViewById(R.id.simpleListView);

        add.setOnClickListener(this);
        view0.setOnClickListener(this);
        Final.setOnClickListener(this);
        naam.setOnClickListener(this);
        roll.setOnClickListener(this);
        mail.setOnClickListener(this);
        phon.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addinfo:
                boolean isInserted = myDb.insertData(naam.getText().toString(),
                        mail.getText().toString(),
                        phon.getText().toString());
                if (isInserted == true) {
                    naam.setText("");
                    phon.setText("");
                    mail.setText("");
                    roll.setText("");
                    Toast.makeText(AddActivity.this, "data found", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(AddActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                break;
            case R.id.viewdata:
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Roll :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Email :" + res.getString(2) + "\n");
                    buffer.append("Phone :" + res.getString(3) + "\n\n");
                }

                // Show all data
                showMessage("Data", buffer.toString());
                break;
            case R.id.finish:
                Intent finishIntent = new Intent(AddActivity.this, UserAreaActivity.class);
                startActivity(finishIntent);
                break;


        }
    }

    public void showMessage(String title, String msg) {
        AlertDialog.Builder bulider = new AlertDialog.Builder(this);
        bulider.setCancelable(true);
        bulider.setTitle(title);
        bulider.setMessage(msg);
        bulider.show();
    }
}


