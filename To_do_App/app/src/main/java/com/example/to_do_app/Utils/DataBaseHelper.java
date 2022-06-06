package com.example.to_do_app.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.to_do_app.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;


    private static final String Database_name = "TODO_DATABASE";
    private static final String Table_name = "TODO_TABLE";
    private static final String Col_1 = "Id";
    private static final String Col_2 = "Task";
    private static final String Col_3 = "Status";

    public DataBaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_name + "(Id INTEGER PRIMARY KEY AUTOINCREMENT,TASK TEXT,STATUS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);
    }

    public void insertTask(ToDoModel model) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_2, model.getTask());
        values.put(Col_3, 0);
        db.insert(Table_name, null, values);

    }

    public void updateTask(int id, String task) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_2, task);
        db.update(Table_name, values, "Id=?", new String[]{String.valueOf(id)});


    }


    public void updateStatus(int id, int status) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_3, status);
        db.update(Table_name, values, "ID=?", new String[]{String.valueOf(id)});
    }


    public void deleteTask(int id) {
        db = this.getWritableDatabase();
        db.delete(Table_name, "ID=?", new String[]{String.valueOf(id)});


    }


    @SuppressLint("Range")
    public List<ToDoModel> getAllTask() {
        db = this.getWritableDatabase();
        Cursor cursor = null;
        List<ToDoModel> modelList = new ArrayList<>();
        db.beginTransaction();
        try {
            cursor = db.query(Table_name, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setId(cursor.getInt(cursor.getColumnIndex(Col_1)));
                        task.setTask(cursor.getString(cursor.getColumnIndex(Col_2)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndex(Col_3)));
                        modelList.add(task);

                    } while (cursor.moveToNext());
                }
            }

            }finally {
                db.endTransaction();
                cursor.close();
            }
            return modelList;
        }
        }


