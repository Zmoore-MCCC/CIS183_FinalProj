package com.example.finalprojdatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = DatabaseInfo.getDbName();
    private static final String TABLE_USERS = DatabaseInfo.getTableNameUsers();
    private static final String TABLE_CARDS = DatabaseInfo.getTableNameCards();
    private static final String TABLE_CATEGORY = DatabaseInfo.getTableNameCategory();
    private static final String TABLE_LOCATION = DatabaseInfo.getTableNameLocation();
    private static final String TABLE_TRANSACTIONS = DatabaseInfo.getTableNameTransactions();

    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, 5);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table if not exists " + TABLE_USERS + " (username varchar(255) primary key not null,fname varchar (255),lname varchar (255),password varchar (255));");
        db.execSQL("Create table if not exists " + TABLE_CARDS + " (cardId integer primary key autoincrement not null,cardname varchar (255),username varchar (255),foreign key (username) references users (username));");
        db.execSQL("Create table if not exists " + TABLE_CATEGORY + " (categoryId integer primary key autoincrement not null,name varchar (255));");
        db.execSQL("Create table if not exists " + TABLE_LOCATION + " (locationId integer primary key autoincrement not null,name varchar (255));");
        db.execSQL("Create table if not exists " + TABLE_TRANSACTIONS + " (transactionId integer primary key autoincrement not null, cardId int,date datetime default (datetime('now','localtime')),categoryId int,locationId int,amount double,foreign key (cardId) references cards (cardId),foreign key (locationId) references location (locationId),foreign key (categoryId) references category (categoryId));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS + ";");

        onCreate(db);

    }
    public void initAllTables()
    {
        init_Users();
        init_Cards();
        init_Categories();
        init_Locations();
        init_Transactions();
    }
    public boolean init_Users()
    {
        if(countRecordsFromTable(TABLE_USERS) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL("insert into " + TABLE_USERS + " values ('zmoore', 'Zackary', 'Moore', 'pass123');");
            db.execSQL("insert into " + TABLE_USERS + " values ('sthomas', 'Shannon', 'Thomas', 'password');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean init_Cards()
    {
        if(countRecordsFromTable(TABLE_CARDS) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL("insert into " + TABLE_CARDS + " (cardname, username) values ('Capital One', 'zmoore');");
            db.execSQL("insert into " + TABLE_CARDS + " (cardname, username) values ('Visa', 'zmoore');");
            db.execSQL("insert into " + TABLE_CARDS + " (cardname, username) values ('Capital One', 'sthomas');");
            db.execSQL("insert into " + TABLE_CARDS + " (cardname, username) values ('Master Card', 'sthomas');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean init_Categories()
    {
        if(countRecordsFromTable(TABLE_CATEGORY) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL("insert into " + TABLE_CATEGORY + " (name) values ('Grocery Store');");
            db.execSQL("insert into " + TABLE_CATEGORY + " (name) values ('Gas');");
            db.execSQL("insert into " + TABLE_CATEGORY + " (name) values ('Food Takeout');");
            db.execSQL("insert into " + TABLE_CATEGORY + " (name) values ('Fun');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean init_Locations()
    {
        if(countRecordsFromTable(TABLE_LOCATION) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL("insert into " + TABLE_LOCATION + " (name) values ('Meijer');");
            db.execSQL("insert into " + TABLE_LOCATION + " (name) values ('Target');");
            db.execSQL("insert into " + TABLE_LOCATION + " (name) values ('Kroger');");
            db.execSQL("insert into " + TABLE_LOCATION + " (name) values ('Taco Bell');");
            db.execSQL("insert into " + TABLE_LOCATION + " (name) values ('DTE');");
            db.execSQL("insert into " + TABLE_LOCATION + " (name) values ('BP Gas');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean init_Transactions()
    {
        if(countRecordsFromTable(TABLE_TRANSACTIONS) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, categoryId, locationId, amount) values (1, 1, 1, 75.12);");
            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, categoryId, locationId, amount) values (1, 3, 4, 30.19);");
            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, categoryId, locationId, amount) values (1, 3, 4, 15.75);");
            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, categoryId, locationId, amount) values (1, 2, 6, 75.12);");
            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, categoryId, locationId, amount) values (3, 2, 6, 75.12);");
            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, date, categoryId, locationId, amount) values (3, '2023-10-05', 2, 6, 45.15);");
            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, date, categoryId, locationId, amount) values (3, '2023-10-23', 2, 6, 56.75);");
            db.execSQL("insert into " + TABLE_TRANSACTIONS + " (cardId, date, categoryId, locationId, amount) values (4, '2023-10-15', 2, 6, 23.19);");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }


    public int countRecordsFromTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        db.close();

        return numRows;
    }
    public boolean usernameExists(String username)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String checkUsername = "Select count(username) from " + TABLE_USERS + " where username = '" + username + "';";
        Cursor cursor = db.rawQuery(checkUsername, null);
        //move cursor to the first thing because there should only be one thing returned.
        cursor.moveToFirst();
        //give getnInt 0 for the first thing that is returned.  This should always return one thing because I am using the count function in sql
        int count = cursor.getInt(0);

        db.close();

        if(count != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean validUsernamePasswordCombo(String username, String password)
    {
        boolean goodUsernamePassword = false;
        if(usernameExists(username))
        {
            String getUserInfo = "Select password from " + TABLE_USERS + " WHERE username = '" + username + "';";

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(getUserInfo, null);

            if(cursor != null)
            {
                cursor.moveToFirst();

                //no need for a loop.  Should only be one thing returned to us
                //we do not need to use getcolumindex because this should only return us one column because we are selecting password only in our query
                if(password.equals(cursor.getString(0).toString()))
                {
                    goodUsernamePassword = true;
                }
                else
                {
                    Log.d("BAD PASSWORD: ", "the password entered is not the correct password for that username");
                    goodUsernamePassword = false;
                }
            }
        }
        else
        {
            Log.d("BAD USERNAME: ", "the username entered was not found in the db");
            goodUsernamePassword = false;
        }



        return goodUsernamePassword;

    }
}
