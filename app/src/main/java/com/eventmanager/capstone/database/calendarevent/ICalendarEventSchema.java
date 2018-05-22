package com.eventmanager.capstone.database.calendarevent;

/**
 * Created by alex on 2018-03-12.
 */

public interface ICalendarEventSchema {

    // COLUMNS NAME
    String CALENDAR_EVENT_TABLE = "calendar_event_table";
    String COLUMN_CALENDAR_EVENT_ID = "calendar_event_id";
    String COLUMN_NAME = "name";
    String COLUMN_DAY_OF_MONTH = "day_of_month";
    String COLUMN_MONTH = "month";
    String COLUMN_YEAR = "year";
    String COLUMN_TIME_START = "time_start";
    String COLUMN_TIME_END = "time_end";
    String COLUMN_LOCATION = "location";
    String COLUMN_DESCRIPTION = "description";

    // ON CREATE
    String SQL_CREATE_TABLE_LIST_ITEM = "CREATE TABLE "
            + CALENDAR_EVENT_TABLE + " ("
            + COLUMN_CALENDAR_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DAY_OF_MONTH + " INTEGER,"
            + COLUMN_MONTH + " INTEGER,"
            + COLUMN_YEAR + " INTEGER,"
            + COLUMN_TIME_START + " TEXT,"
            + COLUMN_TIME_END + " TEXT,"
            + COLUMN_LOCATION + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT" + ");";

    // ON DELETE
    String SQL_DELETE_TABLE_CALENDAR_EVENT = "DROP TABLE IF EXISTS " + CALENDAR_EVENT_TABLE;
}
