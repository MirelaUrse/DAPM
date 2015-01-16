package com.example.oana.studnote;

import android.widget.BaseAdapter;
import android.widget.TimePicker;


/**
 * Created by ohotescu on 20/08/14.
 */
public class TimetableListItem {
    private String event;
    private String hour;//permits to choose the hour of the day
    private int requestCode;
    private BaseAdapter adapter;



    public TimetableListItem(String event, String hour, int requestCode) {
        this.event = event;
        this.requestCode = requestCode;
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
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




}
