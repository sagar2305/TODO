package com.todoapp.simpletodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by SagarMutha on 8/13/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TasksDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TASKS = "Tasks";

    // Table columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_COMPLETED = "completed";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_DATE + " TEXT," + KEY_COMPLETED + " INTEGER DEFAULT 0)";
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(database);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());

        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

        String date = "";
        if (task.getDueDate() != null) {
            date = sdf.format(task.getDueDate());
        }
        values.put(KEY_DATE, date);

        int isComplete = task.isCompleted() ? 1 : 0;
        values.put(KEY_COMPLETED, isComplete);

        int id = (int)db.insert(TABLE_TASKS, null, values);
        task.setID(id);

        db.close();
    }

    // Getting All Tasks
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                Task task = new Task(id, name);

                String date = cursor.getString(2);
                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

                try {
                    task.setDueDate(sdf.parse(date));
                } catch (ParseException pe) {

                }
                boolean isCompleted = (cursor.getInt(3) == 1);
                task.setCompleted(isCompleted);

                //add task to the list
                taskList.add(task);

            } while (cursor.moveToNext());
        }

        return taskList;
    }

    // Updating single task
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());

        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

        Date date = task.getDueDate();
        if (date != null) {
            String dateStr = sdf.format(date);
            values.put(KEY_DATE, dateStr);
        }

        int isComplete = task.isCompleted() ? 1 : 0;
        values.put(KEY_COMPLETED, isComplete);

        // update row
        return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getID()) });
    }

    // Delete task
    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getID()) });
        db.close();
    }
}
