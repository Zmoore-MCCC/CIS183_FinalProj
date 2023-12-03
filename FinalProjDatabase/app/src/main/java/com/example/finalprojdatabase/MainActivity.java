package com.example.finalprojdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        dbHelper.initAllTables();

        Log.d("USERS Count: ", dbHelper.countRecordsFromTable("Users") + "");
        Log.d("CARDS Count: ", dbHelper.countRecordsFromTable("Cards") + "");
        Log.d("CATEGORIES Count: ", dbHelper.countRecordsFromTable("Category") + "");
        Log.d("LOCATION Count: ", dbHelper.countRecordsFromTable("Location") + "");
        Log.d("TRANSACTIONS Count: ", dbHelper.countRecordsFromTable("Transactions") + "");
    }
}