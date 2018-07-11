package com.eventmanager.capstone.models;

import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alex on 1/28/2018.
 */

public class CalendarEventModel {
    private int mCalendarEventId;
    private String mName;
    private int mDayOfMonth;
    private int mMonth;
    private int mYear;
    private String mStartTime;
    private String mEndTime;
    private String mLocation;
    private String mDescription;

    public CalendarEventModel(int calendarEventId, String name, int dayOfMonth, int month, int year, String startTime, String endTime, String location, String description) {
        this.mYear = year;
        this.mCalendarEventId = calendarEventId;
        this.mName = name;
        this.mDayOfMonth = dayOfMonth;
        this.mMonth = month;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mLocation = location;
        this.mDescription = description;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        this.mYear = year;
    }

    public int getCalendarEventId() {
        return mCalendarEventId;
    }

    public void setCalendarEventId(int calendarEventId) {
        this.mCalendarEventId = calendarEventId;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getDayOfMonth() {
        return mDayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.mDayOfMonth = dayOfMonth;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        this.mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        this.mEndTime = endTime;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }


    public WeekViewEvent toWeekViewEvent(){

        // Parse time.



        // Initialize start and end time.
        Calendar now = Calendar.getInstance();

        Calendar startTime = (Calendar) now.clone();
        startTime.setTimeInMillis(getParsedStartTime().getTime());
        startTime.set(Calendar.YEAR, getYear());
        startTime.set(Calendar.MONTH, getMonth());
        startTime.set(Calendar.DAY_OF_MONTH, getDayOfMonth());

        Calendar endTime = (Calendar) startTime.clone();
        endTime.setTimeInMillis(getParsedEndTime().getTime());
        endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
        endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
        endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));

        // Create an week view event.
        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setId(getCalendarEventId());
        weekViewEvent.setName(getName());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
//        weekViewEvent.setColor(getColor());

        return weekViewEvent;
    }

    public Date getParsedStartTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date start = new Date();
        try {
            start = sdf.parse(getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    public Date getParsedEndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date end = new Date();
        try {
            end = sdf.parse(getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }
}
