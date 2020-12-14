package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText uname,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialise the objects with the resource ids
        uname = findViewById(R.id.Uname);
        password = findViewById(R.id.Pword);
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Boolean status=false;
        String username = uname.getText().toString();
        System.out.println(username);
        String pwd = password.getText().toString();

        //Checks if the login is valid. compares the login username and password and throws error if not correct or empty
        while (!status) {
            if (username.equals("student1") && pwd.equals("123456")) {
                Intent intent = new Intent(this, CourseList.class);
                //passing the username to the next activity.
                intent.putExtra("uname", uname.getText().toString());
                startActivity(intent);

                status = true;
                //checks empty condition
            } else if (username.equals("") || pwd.equals("")) {
                Toast.makeText(MainActivity.this, "UserName or Password Cannot be empty",
                        Toast.LENGTH_LONG).show();
                status = false;
            } else {
                Toast.makeText(MainActivity.this, "Invalid UserName or Password",
                        Toast.LENGTH_LONG).show();
                status = false;
            }

        }
    }
}