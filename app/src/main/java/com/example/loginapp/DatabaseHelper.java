package com.example.loginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME="RecipeList.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_library";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_Name="recipe_name";
    public static final String COLUMN_INGREDIENTS="recipe_ingredients";
    public static final String COLUMN_STEPS="recipe_steps";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE" + TABLE_NAME +
                        " (" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_STEPS +
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
