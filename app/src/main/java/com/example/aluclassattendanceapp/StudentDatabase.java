package com.example.aluclassattendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StudentDatabase extends SQLiteOpenHelper {

    //    Name of the Database and version
    public static final String databaseName = "student_database_test";
    public static final int databaseVersion = 1;
    public static final String TABLE_NAME = "students";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public StudentDatabase(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + NAME + "TEXT" + EMAIL + "TEXT" + PASSWORD + "TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS students(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, email VARCHAR, password VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void trial1(Context context, String name, String email, String password) {
        SQLiteDatabase database = getWritableDatabase();
/*
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, password);

        database.insert(TABLE_NAME, null, contentValues);*/

        database.execSQL("INSERT INTO students (name,email, password) VALUES('" + name + "', '" + email + "', '" + password + "');");

        Toast.makeText(context, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
    }

    public List<List<String>> getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<List <String>> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from students", null);

        if (cursor.moveToFirst()) {
            do {
                List<String> list1 = new ArrayList<>();
                list1.add(cursor.getString(cursor.getColumnIndex("name")));
                list1.add(cursor.getString(cursor.getColumnIndex("email")));
                list1.add(cursor.getString(cursor.getColumnIndex("password")));
                list.add(list1);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void delete(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from students where email='"+email+"'");
    }

    public void update(String email, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("update students set name='"+name+"'where email='"+email+"'");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }
}
