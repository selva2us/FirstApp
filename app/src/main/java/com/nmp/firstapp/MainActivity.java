package com.nmp.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public PreparedStatement dbConnection(String sql) {
        PreparedStatement stmt = null;
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            Connection con = connectionDB.connection();
            stmt = con.prepareStatement(sql);
        } catch (Exception e) {
            Log.i("Connection Exception", e.getMessage());
        }
        return stmt;
    }

    public void login(View view){
        String sql = "SELECT * FROM NMI_LOGIN WHERE user_name=(?) AND pass_word=(?) ;";

            EditText name = (EditText) findViewById(R.id.etusername);
            EditText password = (EditText) findViewById(R.id.etpassword);
            Button login = (Button) findViewById(R.id.etlogin);
            String USERNAME = name.getText().toString();
            String PASSWORD =password.getText().toString();
            CharSequence text;
            if(USERNAME.trim().equals("") && PASSWORD.trim().equals(""))
            {
                Toast.makeText(this, "please add username and passwod", Toast.LENGTH_SHORT).show();
            }
            else {
                try {

                    Log.i("username:", USERNAME);
                    Log.i("password:", PASSWORD);
                    Log.i("sql:", sql);

                    PreparedStatement stmt = dbConnection(sql);
                    stmt.setString(1, USERNAME);
                    stmt.setString(2, PASSWORD);
                    ResultSet rs = stmt.executeQuery();

                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_LONG;

                    if (rs.next() == Boolean.TRUE) {
                        System.out.println("Login Success");
                        text = "Login Success";
                        System.out.println(rs.getString(1) + " " + rs.getString(2));
                        openactivity();


                    } else {
                        text = "Login failed";
                        System.out.println("Login Failed");
                    }

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 10, 10);
                    toast.show();

                } catch (Exception e) {
                    //Toast.makeText(e.getMessage());
                    Log.i("Connection Exception", e.getMessage());
                }
            }

    }
    public void openactivity()
    {
        Intent intent = new Intent(this, ViewModelActivity.class);
        startActivity(intent);
    }

}
