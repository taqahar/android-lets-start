package com.abq.puissance.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    private final String MATIERE_CREATE = "CREATE TABLE matiere(" +
            "id INT PRIMARY KEY," +
            "libelle VARCHAR(40)," +
            "facultatif INT(1)," +
            "enseignant VARCHAR(40)," +
            "image_name VARCHAR(40)" +
            ");";
    public DBHelper(@Nullable Context context) {
        super(context, "matiereDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MATIERE_CREATE);
        this.database = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.database = db;
    }

    public SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
}
