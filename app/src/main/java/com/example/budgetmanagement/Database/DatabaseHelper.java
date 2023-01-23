package com.example.budgetmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String DB_Name = "fb_budget_management";
    private static final int DB_Version = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: started");
        String createUserTable = "CREATE TABLE users (_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL, " +
                "password TEXT NOT NULL, first_name TEXT, last_name TEXT, address TEXT, image_url TEXT, remained_amount DOUBLE)";

        String createShoppingTable = "CREATE TABLE shopping (_id INTEGER PRIMARY KEY AUTOINCREMENT, item_id INTEGER, " +
                "user_id INTEGER, transaction_id INTEGER, price DOUBLE, date DATE, description TEXT)";

        String createInvestmentTable = "CREATE TABLE investments (_id INTEGER PRIMARY KEY AUTOINCREMENT, amount DOUBLE, " +
                "monthly_roi DOUBLE, name TEXT, init_date DATE, finish_date DATE, user_id INTEGER, transaction_id INTEGER)";

        String createLoansTable = "CREATE TABLE loans (_id INTEGER PRIMARY KEY AUTOINCREMENT, init_date DATE, " +
                "finish_date DATE, init_amount DOUBLE, remained_amount DOUBLE, monthly_payment DOUBLE, monthly_roi DOUBLE," +
                "name TEXT, user_id INTEGER, transaction_id INTEGER)";

        String createTransactionTable = "CREATE TABLE transactions (_id INTEGER PRIMARY KEY AUTOINCREMENT, amount DOUBLE," +
                "date DATE, type TEXT, user_id INTEGER, recipient TEXT, description TEXT)";

        String createItemsTable = "CREATE TABLE items (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image_url TEXT," +
                "description TEXT)";

        sqLiteDatabase.execSQL(createUserTable);
        sqLiteDatabase.execSQL(createShoppingTable);
        sqLiteDatabase.execSQL(createInvestmentTable);
        sqLiteDatabase.execSQL(createLoansTable);
        sqLiteDatabase.execSQL(createTransactionTable);
        sqLiteDatabase.execSQL(createItemsTable);

        addInitialItems(sqLiteDatabase);

        addTestTransaction(sqLiteDatabase);
        addTestProfit(sqLiteDatabase);
        addTestShopping(sqLiteDatabase);
    }

    private void addTestShopping(SQLiteDatabase db) {
        Log.d(TAG, "addTestShopping: started");
        ContentValues firstValues = new ContentValues();
        firstValues.put("item_id", 1);
        firstValues.put("transaction_id", 1);
        firstValues.put("user_id", 1);
        firstValues.put("price", 10.0);
        firstValues.put("description", "some description");
        firstValues.put("date", "2019-10-01");
        db.insert("shopping", null, firstValues);

        ContentValues secondValues = new ContentValues();
        secondValues.put("item_id", 2);
        secondValues.put("transaction_id", 2);
        secondValues.put("user_id", 1);
        secondValues.put("price", 8.0);
        secondValues.put("description", "second description");
        secondValues.put("date", "2022-12-01");
        db.insert("shopping", null, secondValues);

        ContentValues thirdValues = new ContentValues();
        thirdValues.put("item_id", 2);
        thirdValues.put("transaction_id", 3);
        thirdValues.put("user_id", 1);
        thirdValues.put("price", 16.0);
        thirdValues.put("description", "third description");
        thirdValues.put("date", "2022-11-02");
        db.insert("shopping", null, thirdValues);
    }

    private void addTestProfit(SQLiteDatabase db) {
        Log.d(TAG, "addTestProfit: started");
        ContentValues firstValues = new ContentValues();
        firstValues.put("amount", 15.0);
        firstValues.put("type", "profit");
        firstValues.put("date", "2022-11-10");
        firstValues.put("description", "monthly profit from StanChart");
        firstValues.put("user_id", 1);
        firstValues.put("recipient", "StanChart");
        db.insert("transactions", null, firstValues);

        ContentValues secondValues = new ContentValues();
        secondValues.put("amount", 25.0);
        secondValues.put("type", "profit");
        secondValues.put("date", "2011-11-26");
        secondValues.put("description", "monthly profit from Real Estate investment");
        secondValues.put("user_id", 1);
        secondValues.put("recipient", "Real Estate Agency");
        db.insert("transactions", null, secondValues);

        ContentValues thirdValues = new ContentValues();
        thirdValues.put("amount", 32.0);
        thirdValues.put("type", "profit");
        thirdValues.put("date", "2022-11-11");
        thirdValues.put("description", "monthly profit stocks");
        thirdValues.put("user_id", 1);
        thirdValues.put("recipient", "Investment Bank");
        db.insert("transactions", null, thirdValues);
    }

    private void addTestTransaction(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "addTestTransaction: started");
        ContentValues values = new ContentValues();
        values.put("_id", 0);
        values.put("amount", 10.5);
        values.put("date", "2022-11-04");
        values.put("type", "shopping");
        values.put("user_id", 1);
        values.put("description", "Grocery shopping");
        values.put("recipient", "Chandarana");
        long newTransactionId = sqLiteDatabase.insert("transactions", null, values);
        Log.d(TAG, "addTestTransaction: transaction id: " + newTransactionId);
    }

    private void addInitialItems(SQLiteDatabase db) {
        Log.d(TAG, "addInitialItems: started");
        ContentValues firstvalues = new ContentValues();
        firstvalues.put("name", "Bike");
        firstvalues.put("image_url", "https://www.pexels.com/photo/black-and-white-hardtail-bike-on-brown-road-between-trees-100582/");
        firstvalues.put("description", "A nice mountain bike.");
        db.insert("items", null, firstvalues);

        ContentValues secondvalues = new ContentValues();
        secondvalues.put("name", "Milk");
        secondvalues.put("image_url", "https://www.pexels.com/photo/black-and-white-hardtail-bike-on-brown-road-between-trees-100582/");
        secondvalues.put("description", "Some Milk.");
        db.insert("items", null, secondvalues);

        ContentValues thirdvalues = new ContentValues();
        thirdvalues.put("name", "Bread");
        thirdvalues.put("image_url", "https://www.pexels.com/photo/black-and-white-hardtail-bike-on-brown-road-between-trees-100582/");
        thirdvalues.put("description", "Loaf of bread.");
        db.insert("items", null, thirdvalues);

        ContentValues forthvalues = new ContentValues();
        forthvalues.put("name", "Eggs");
        forthvalues.put("image_url", "https://www.pexels.com/photo/black-and-white-hardtail-bike-on-brown-road-between-trees-100582/");
        forthvalues.put("description", "Some eggs.");
        db.insert("items", null, forthvalues);

        ContentValues fifthvalues = new ContentValues();
        fifthvalues.put("name", "Butter");
        fifthvalues.put("image_url", "https://www.pexels.com/photo/black-and-white-hardtail-bike-on-brown-road-between-trees-100582/");
        fifthvalues.put("description", "Cooking butter.");
        db.insert("items", null, fifthvalues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
