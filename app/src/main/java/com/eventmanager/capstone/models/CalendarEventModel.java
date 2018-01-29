package com.eventmanager.capstone.models;

import com.alamkanak.weekview.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alex on 1/28/2018.
 */

public class CalendarEventModel {
    private String mName;
    private int mDayOfMonth;
    private int mMonth;
    private String mStartTime;
    private String mEndTime;
    private int mColor;

    public CalendarEventModel(String name, int dayOfMonth, int month, String startTime, String endTime, int color) {
        this.mName = name;
        this.mDayOfMonth = dayOfMonth;
        this.mMonth = month;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mColor = color;
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

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public WeekViewEvent toWeekViewEvent(){

        // Parse time.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date start = new Date();
        Date end = new Date();
        try {
            start = sdf.parse(getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            end = sdf.parse(getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Initialize start and end time.
        Calendar now = Calendar.getInstance();

        Calendar startTime = (Calendar) now.clone();
        startTime.setTimeInMillis(start.getTime());
        startTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
        startTime.set(Calendar.MONTH, getMonth());
        startTime.set(Calendar.DAY_OF_MONTH, getDayOfMonth());

        Calendar endTime = (Calendar) startTime.clone();
        endTime.setTimeInMillis(end.getTime());
        endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
        endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
        endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));

        // Create an week view event.
        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName(getName());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
        weekViewEvent.setColor(getColor());

        return weekViewEvent;
    }
}
