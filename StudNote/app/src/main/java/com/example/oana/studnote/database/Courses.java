package com.example.oana.studnote.database;

import java.util.Date;

/**
 * Created by Oana on 5/4/2015.
 */
public class Courses {
    private int id;
    private String name;
    private String teacher;
    private String time;
    private String place;
    private String grading;
    private String other;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getOther() {
        return other;
    }

    public String getGrading() {
        return grading;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setGrading(String grading) {
        this.grading = grading;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", grading='" + grading + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
