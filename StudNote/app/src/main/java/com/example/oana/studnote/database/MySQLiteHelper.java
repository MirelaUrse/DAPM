package com.example.oana.studnote.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Oana on 2/24/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_COURSES = "courses";
    public static final String TABLE_TIMETABLE= "timetable";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COURSES_NAME = "courses_name";
    public static final String COLUMN_COURSES_TEACHER = "courses_teacher";
    public static final String COLUMN_COURSES_TIME = "courses_time";
    public static final String COLUMN_COURSES_PLACE = "courses_place";
    public static final String COLUMN_COURSES_GRADING = "courses_grading";
    public static final String COLUMN_COURSES_OTHER = "courses_other";

    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_EVENT = "event";

    private static final String DATABASE_NAME = "schedule.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_COURSES= "CREATE TABLE "+ TABLE_COURSES+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_COURSES_NAME+" TEXT, "+ COLUMN_COURSES_TEACHER +" TEXT, "+COLUMN_COURSES_TIME+" TEXT, "+COLUMN_COURSES_PLACE+" TEXT, "+COLUMN_COURSES_GRADING+" TEXT, "+COLUMN_COURSES_OTHER+" TEXT)";
    private static final String DATABASE_CREATE_TIMETABLE= "CREATE TABLE "+ TABLE_TIMETABLE+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_DAY+" TEXT, "+ COLUMN_TIME+" TEXT, "+COLUMN_EVENT+" TEXT)";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_COURSES);
        db.execSQL(DATABASE_CREATE_TIMETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
        onCreate(db);
    }
}
