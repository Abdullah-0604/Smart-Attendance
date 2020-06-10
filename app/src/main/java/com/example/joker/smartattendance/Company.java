package com.example.joker.smartattendance;

import java.io.Serializable;

/**
 * Created by Dell on 1/3/2018.
 */

public class Company implements Serializable {

    public static final String TAG = "Employee";
    private static final long serialVersionUID = -7406082437623008161L;

    private long dId;
    private String dName;
    private String year;
    private String mPhoneNumber;
    private String semester;

    public Company() {
    }


    public Company(String name, String yr, String phoneNumber, String sem) {
        this.dName = name;
        this.year = yr;
        this.mPhoneNumber = phoneNumber;
        this.semester = sem;
    }

    public long getdId() {
        return dId;
    }

    public void setdId(long dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}