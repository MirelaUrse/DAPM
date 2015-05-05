package com.example.oana.studnote.database;

/**
 * Created by Oana on 5/5/2015.
 */
public class Timetable {
    private int id;
    private String event;
    private String day;
    private String hour;

    public int getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", event='" + event + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
