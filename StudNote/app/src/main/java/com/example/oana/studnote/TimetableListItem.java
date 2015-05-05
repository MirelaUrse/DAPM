package com.example.oana.studnote;

import android.widget.BaseAdapter;
import android.widget.TimePicker;

import com.example.oana.studnote.database.Timetable;


/**
 * Created by ohotescu on 20/08/14.
 */
public class TimetableListItem {
    private String event;
    private String day;
    private String hour;//permits to choose the hour of the day
    private int requestCode;
    private BaseAdapter adapter;



    public TimetableListItem(String day, String hour,String event, int requestCode) {
        this.event = event;
        this.day=day;
        this.hour=hour;
        this.requestCode = requestCode;
    }
    public TimetableListItem(Timetable timetable, int requestCode) {
        this.event = timetable.getEvent();
        this.day=timetable.getDay();
        this.hour=timetable.getHour();
        this.requestCode = requestCode;
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
    public String getHour() {
        return hour;
    }
    public String getDay() {
        return hour;
    }
}
