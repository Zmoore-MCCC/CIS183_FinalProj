package com.example.finalprojdatabase;

public class DatabaseInfo
{
    private static final String DB_NAME = "FinanceDb.db";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_CARDS = "Cards";
    private static final String TABLE_CATEGORY = "Category";
    private static final String TABLE_LOCATION = "Location";
    private static final String TABLE_TRANSACTIONS = "Transactions";

    public static String getDbName()
    {
        return DB_NAME;
    }

    public static String getTableNameUsers()
    {
        return TABLE_USERS;
    }

    public static String getTableNameCards()
    {
        return TABLE_CARDS;
    }
    public static String getTableNameCategory()
    {
        return TABLE_CATEGORY;
    }
    public static String getTableNameLocation()
    {
        return TABLE_LOCATION;
    }
    public static String getTableNameTransactions()
    {
        return TABLE_TRANSACTIONS;
    }
}
