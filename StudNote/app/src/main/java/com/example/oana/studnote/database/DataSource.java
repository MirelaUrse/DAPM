package com.example.oana.studnote.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oana.studnote.database.Courses;
import com.example.oana.studnote.database.MySQLiteHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the data base that stores the data collected by the app
 * Created by Oana on 2/24/2015.
 */

public class DataSource {
    // Database fields
    private static  String TAG="DataSource";
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allCoursesColumns = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_COURSES_NAME,MySQLiteHelper.COLUMN_COURSES_TEACHER,MySQLiteHelper.COLUMN_COURSES_TIME,MySQLiteHelper.COLUMN_COURSES_PLACE,MySQLiteHelper.COLUMN_COURSES_GRADING,MySQLiteHelper.COLUMN_COURSES_OTHER};
    private String[] allTimetableColumns = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_DAY,MySQLiteHelper.COLUMN_TIME,MySQLiteHelper.COLUMN_EVENT};

    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Courses createCourses(String coursesName,String coursesTeacher,String coursesTime,String coursesPlace,String coursesGrading,String coursesOther) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COURSES_NAME, coursesName);
        values.put(MySQLiteHelper.COLUMN_COURSES_TEACHER, coursesTeacher);
        values.put(MySQLiteHelper.COLUMN_COURSES_TIME, coursesTime.toString());
        values.put(MySQLiteHelper.COLUMN_COURSES_PLACE, coursesPlace);
        values.put(MySQLiteHelper.COLUMN_COURSES_GRADING, coursesGrading);
        values.put(MySQLiteHelper.COLUMN_COURSES_OTHER, coursesOther);
        long insertId = database.insert(MySQLiteHelper.TABLE_COURSES, null,values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COURSES,
                allCoursesColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Courses courses = new Courses();
        courses.setId(cursor.getInt(0));
        courses.setName(cursor.getString(1));
        courses.setTeacher(cursor.getString(2));
        courses.setTime(cursor.getString(3));
        courses.setPlace(cursor.getString(4));
        courses.setGrading(cursor.getString(5));
        courses.setOther(cursor.getString(6));
        cursor.close();
        return courses;
    }

    public void deleteCourses(Courses courses) {
        int id = courses.getId();
        System.out.println("Courses deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COURSES, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Courses> getAllCourses() {
        List<Courses> coursesList = new ArrayList<Courses>();
        try {
            Cursor cursor =database.query(MySQLiteHelper.TABLE_COURSES, allCoursesColumns, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Courses courses = new Courses();
                courses.setId(cursor.getInt(0));
                courses.setName(cursor.getString(1));
                courses.setTeacher(cursor.getString(2));
                courses.setTime(cursor.getString(3));
                courses.setPlace(cursor.getString(4));
                courses.setGrading(cursor.getString(5));
                courses.setOther(cursor.getString(6));
                coursesList.add(courses);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        } catch (Exception e)
        {
            Log.d(TAG,e.getMessage());
        }
        return coursesList;
    }

    public Timetable createTimetable(String day,String time,String event) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DAY, day);
        values.put(MySQLiteHelper.COLUMN_TIME, time);
        values.put(MySQLiteHelper.COLUMN_EVENT, event);
        long insertId = database.insert(MySQLiteHelper.TABLE_TIMETABLE, null,values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_TIMETABLE,
                allTimetableColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Timetable timetable = new Timetable();
        timetable.setId(cursor.getInt(0));
        timetable.setDay(cursor.getString(1));
        timetable.setHour(cursor.getString(2));
        timetable.setEvent(cursor.getString(3));
        cursor.close();
        return timetable;
    }

    public void deleteTimetable(Timetable timetable) {
        int id = timetable.getId();
        System.out.println("Timetable deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_TIMETABLE, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Timetable> getAllTimetable() {
        List<Timetable> timetableList = new ArrayList<Timetable>();
        try {
            Cursor cursor =database.query(MySQLiteHelper.TABLE_TIMETABLE, allTimetableColumns, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Timetable timetable = new Timetable();
                timetable.setId(cursor.getInt(0));
                timetable.setDay(cursor.getString(1));
                timetable.setHour(cursor.getString(2));
                timetable.setEvent(cursor.getString(3));
                timetableList.add(timetable);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        } catch (Exception e)
        {
            Log.d(TAG,e.getMessage());
        }
        return timetableList;
    }

    public List<Timetable> getDailyTimetable(String day) {
        List<Timetable> timetableList = new ArrayList<Timetable>();
        try {
            Cursor cursor =database.query(MySQLiteHelper.TABLE_TIMETABLE, allTimetableColumns, MySQLiteHelper.COLUMN_DAY+" = '"+day+"'", null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Timetable timetable = new Timetable();
                timetable.setId(cursor.getInt(0));
                timetable.setDay(cursor.getString(1));
                timetable.setHour(cursor.getString(2));
                timetable.setEvent(cursor.getString(3));
                timetableList.add(timetable);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        } catch (Exception e)
        {
            Log.d(TAG,e.getMessage());
        }
        return timetableList;
    }
}


