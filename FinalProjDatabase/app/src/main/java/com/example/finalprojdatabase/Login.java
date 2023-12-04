package com.example.finalprojdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    EditText et_j_username;
    EditText et_j_password;
    TextView txt_j_errorMsg;
    Button btn_j_login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GUI
        et_j_username  = findViewById(R.id.et_login_username);
        et_j_password  = findViewById(R.id.et_login_password);
        txt_j_errorMsg = findViewById(R.id.txt_login_errorMsg);
        btn_j_login    = findViewById(R.id.btn_login_login);

        dbHelper = new DatabaseHelper(this);

        //Initialize all tables in the database with pre-defined information.
        dbHelper.initAllTables();

        //testing Db tables
        testingCountRecordsInTables();

        //setup login button event listener
        loginButtonEvent();
    }

    private void testingCountRecordsInTables()
    {
        Log.d("USERS Count: ", dbHelper.countRecordsFromTable(DatabaseInfo.getTableNameUsers()) + "");
        Log.d("CARDS Count: ", dbHelper.countRecordsFromTable(DatabaseInfo.getTableNameCards()) + "");
        Log.d("CATEGORIES Count: ", dbHelper.countRecordsFromTable(DatabaseInfo.getTableNameCategory()) + "");
        Log.d("LOCATION Count: ", dbHelper.countRecordsFromTable(DatabaseInfo.getTableNameLocation()) + "");
        Log.d("TRANSACTIONS Count: ", dbHelper.countRecordsFromTable(DatabaseInfo.getTableNameTransactions()) + "");
    }

    private void loginButtonEvent()
    {
        btn_j_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //make sure user entered information before I call the db helper
                if(!usernamePasswordNull())
                {
                    //call dbHelper and pass the username and password.
                    String username = et_j_username.getText().toString();
                    String password = et_j_password.getText().toString();


                    //GOOD USERNAME
                    if(dbHelper.validUsernamePasswordCombo(username, password))
                    {
                        Log.d("SUCCESSFUL LOGIN", "good username/password");
                        txt_j_errorMsg.setVisibility(View.INVISIBLE);

                        //set the username in AppData so I can access it across all intents
                        AppData.setUsername(username);

                        //load homepage
                        //Intent homePage = new Intent(Login.this, Homepage.class);
                        //startActivity(homePage);

                        startActivity(new Intent(Login.this, Homepage.class));
                    }
                    //ERROR MESSAGE: either invalid username/password
                    //You can determine if the username/password or both are correct on the DatabaseHelper.
                    else
                    {
                        txt_j_errorMsg.setVisibility(View.VISIBLE);
                    }

                }
                //ERROR MESSAGE
                else
                {
                    Log.d("NOTHING ENTERED", "You need to enter something for username/password");
                }
            }
        });
    }

    private boolean usernamePasswordNull()
    {
        String username = et_j_username.getText().toString();
        String password = et_j_password.getText().toString();

        if(!username.equals("") && !password.toString().equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}