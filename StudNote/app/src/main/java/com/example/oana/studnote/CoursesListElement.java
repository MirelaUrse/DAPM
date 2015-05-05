package com.example.oana.studnote;

import android.widget.BaseAdapter;

import com.example.oana.studnote.database.Courses;


/**
 * Created by ohotescu on 20/08/14.
 */
public class CoursesListElement {
    private String courses;
    private String teacher;
    private String where;
    private String when;
    private String other;
    private String grading;
    private int requestCode;
    private BaseAdapter adapter;



    public CoursesListElement(String courses, String teacher,String where, String when,String grading, String other, int requestCode) {
        this.courses = courses;
        this.teacher = teacher;
        this.where = where;
        this.when = when;
        this.grading = grading;
        this.other = other;
        this.requestCode = requestCode;
    }

    public CoursesListElement(Courses courses, int requestCode) {
        this.courses = courses.getName();
        this.teacher = courses.getTeacher();
        this.where = courses.getPlace();
        this.when = courses.getTime();
        this.grading = courses.getGrading();
        this.other = courses.getOther();
        this.requestCode = requestCode;
    }


    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setGrading(String grading) {
        this.grading = grading;
    }

    public String getCourses() {
        return courses;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getWhere() {
        return where;
    }

    public String getWhen() {
        return when;
    }

    public String getOther() {
        return other;
    }

    public String getGrading() {
        return grading;
    }

    @Override
    public String toString() {
        return "CoursesListElement{" +
                "courses='" + courses + '\'' +
                ", teacher='" + teacher + '\'' +
                ", where='" + where + '\'' +
                ", when='" + when + '\'' +
                ", other='" + other + '\'' +
                ", grading='" + grading + '\'' +
                ", requestCode=" + requestCode +
                ", adapter=" + adapter +
                '}';
    }
}
