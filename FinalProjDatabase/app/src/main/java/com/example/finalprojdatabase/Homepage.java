package com.example.finalprojdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    TextView txt_j_welcome;
    TextView txt_j_numCards;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //testing
        //Log.d("WELCOME USER: ", AppData.getUsername());

        dbHelper = new DatabaseHelper(this);

        //GUI
        txt_j_welcome = findViewById(R.id.txt_homepage_welcome);
        txt_j_numCards = findViewById(R.id.txt_homepage_numCards);

        setWelcomeMessage();
        setNumberOfCards();
    }

    private void setWelcomeMessage()
    {
        txt_j_welcome.setText("Welcome " + dbHelper.getFirstnameForUser() + "!");
    }

    private void setNumberOfCards()
    {
        txt_j_numCards.setText("Number of Cards: " + dbHelper.getNumberOfCardsForUser());
    }
}