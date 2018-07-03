package com.eventmanager.capstone.database.calendarevent;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eventmanager.capstone.database.DbContentProvider;
import com.eventmanager.capstone.models.CalendarEventModel;

import java.util.ArrayList;
import java.util.List;

public class CalendarEventDao extends DbContentProvider implements  ICalendarEventDao, ICalendarEventSchema{

    public CalendarEventDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public CalendarEventModel fetchCalendarEventId(int calendarEventId) {
        CalendarEventModel calendarEvent = null;
        Cursor cursor = super.rawQuery("SELECT * FROM " + CALENDAR_EVENT_TABLE + " WHERE "
                + COLUMN_CALENDAR_EVENT_ID + "=" + calendarEventId, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                calendarEvent = cursorToEntity(cursor);
            }
            cursor.close();
        }

        return calendarEvent;
    }

    @Override
    public List<CalendarEventModel> fetchAllCalendarEvents() {
        List<CalendarEventModel> calendarEventList = new ArrayList<>();
        Cursor cursor = super.rawQuery("SELECT * FROM " + CALENDAR_EVENT_TABLE, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CalendarEventModel calendarEvent = cursorToEntity(cursor);
                calendarEventList.add(calendarEvent);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return calendarEventList;
    }

    @Override
    public int createCalendarEvent(String name, int dayOfMonth, int month, int year, String startTime, String endTime, String location, String description) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DAY_OF_MONTH, dayOfMonth);
        values.put(COLUMN_MONTH, month);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_TIME_START, startTime);
        values.put(COLUMN_TIME_END, endTime);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_DESCRIPTION, description);

        try {
            return super.insert(CALENDAR_EVENT_TABLE, values);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
            return -1;
        }
    }

    @Override
    public boolean deleteCalendarEvent(int calendarEventId) {
        return super.delete(CALENDAR_EVENT_TABLE, COLUMN_CALENDAR_EVENT_ID + "=" + calendarEventId, null) > 0;
    }

    @Override
    public int fetchNumberOfCalendarEvents() {
        return (int) DatabaseUtils.longForQuery(mDb, "SELECT COUNT(*) FROM " + CALENDAR_EVENT_TABLE, null);
    }


    @Override
    protected CalendarEventModel cursorToEntity(Cursor cursor) {
        int calendarEventId = cursor.getInt(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        int dayOfMonth = cursor.getInt(cursor.getColumnIndex(COLUMN_DAY_OF_MONTH));
        int month = cursor.getInt(cursor.getColumnIndex(COLUMN_MONTH));
        int year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR));
        String startTime = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_START));
        String endTime = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_END));
        String location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
        String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

        return new CalendarEventModel(calendarEventId, name, dayOfMonth, month,year, startTime, endTime, location, description);
    }

}
