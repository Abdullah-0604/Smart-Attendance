package com.example.joker.smartattendance;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetStarted extends AppCompatActivity {
private static EditText username;
private static EditText password;
private static Button button_login;
private static Button button_signup;
private static TextView attempts;
int attempt_counter=5;
SQLiteDatabase db;
DatabaseHelperLogin help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        help = new DatabaseHelperLogin(this);

        onclicklogin();
        onclicksignup();
    }

    public void onclicklogin(){
        username = (EditText) findViewById(R.id.editText_username);
        final  String usernamestr = username.getText().toString();
        password = (EditText) findViewById(R.id.editText_password);
        final String passwordstr = password.getText().toString();
        button_login = (Button) findViewById(R.id.button_login);
        attempts = (TextView) findViewById(R.id.attempt_count);

        attempts.setText(Integer.toString(attempt_counter));

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                db = help.getReadableDatabase();
                Cursor cursor = db.rawQuery(" select * from " + help.TABLE_NAME + " where " + help.COL_UNAME + "=? and " + help.COL_PASS + " =?", new String[]{usernamestr,passwordstr});
                if( cursor != null) {
                    if (cursor.getCount()>0) {
                        cursor.moveToFirst();
                             Toast.makeText(GetStarted.this, " LOGIN SUCCESSFUL ", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(GetStarted.this,ListCompaniesActivity.class);
                             startActivity(intent);
                    }



                    else
                    {

                        Toast.makeText(GetStarted.this,"Username and Password is incorrect", Toast.LENGTH_SHORT).show();
                        attempt_counter--;
                        attempts.setText(Integer.toString(attempt_counter));
                        if(attempt_counter==0)
                        {
                            button_login.setEnabled(false);
                        }
                    }

                }



            }
        });

    }

    public void onclicksignup(){

        button_signup = (Button) findViewById(R.id.button_signup);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.joker.smartattendance.Registration");
                startActivity(intent);
            }
        });
    }
}
