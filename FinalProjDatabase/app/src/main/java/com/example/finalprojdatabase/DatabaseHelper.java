package com.example.finalprojdatabase;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "FinanceDb.db";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_CARDS = "Cards";
    private static final String TABLE_CATEGORY = "Category";
    private static final String TABLE_LOCATION = "Location";
    private static final String TABLE_TRANSACTIONS = "Transactions";

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
}
