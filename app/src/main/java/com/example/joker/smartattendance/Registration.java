package com.example.joker.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
private static EditText add_username;
private static EditText add_password;
private static EditText confirm_password;
private static CardView button_register;
DatabaseHelperLogin helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        helper = new DatabaseHelperLogin(this);
        onclick_register();
    }

    public void onclick_register(){
        add_username = (EditText) findViewById(R.id.editText_username_register);
        final String userstr= add_username.getText().toString();
        add_password = (EditText) findViewById(R.id.editText_password_register);
        final String passstr = add_password.getText().toString();
        confirm_password = (EditText) findViewById(R.id.editText_confirm_password);
        final String confirmstr = confirm_password.getText().toString();
        button_register = (CardView) findViewById(R.id.button_register_new);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!passstr.equals(confirmstr)){


                    Toast.makeText(Registration.this,"Passwords donot match",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean isInserted = helper.insertdata(userstr,passstr);
                    if(isInserted == true)
                    {
                        Toast.makeText(Registration.this,"Data stored",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Registration.this,GetStarted.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Registration.this,"Data not stored",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });




    }


}
