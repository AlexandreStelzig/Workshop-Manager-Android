package com.eventmanager.capstone.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eventmanager.capstone.database.calendarevent.CalendarEventDao;
import com.eventmanager.capstone.database.calendarevent.ICalendarEventSchema;

public class Database {
    private static final String TAG = "WorkshopAndroid";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WorkshopAndroid.db";

    private static Database database = null;
    private static DatabaseHelper mDatabaseHelper;

    // !!! for testing purposes only !!!
    private static final boolean RESET_DATABASE_ON_OPEN = true;

    // tables
    public static CalendarEventDao mCalendarEventDao;

    private Database(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        // init tables
        mCalendarEventDao = new CalendarEventDao(sqLiteDatabase);
        createCalendarEvents();
    }

    private void createCalendarEvents() {
        // counter starts at 0 for months
        mCalendarEventDao.createCalendarEvent("Event1", 20, 4, 2018,"10:30", "11:30","location1", "description1");
        mCalendarEventDao.createCalendarEvent("Event2", 21, 4, 2018,"10:30", "11:30","location2", "description2");
        mCalendarEventDao.createCalendarEvent("Event3", 22, 4, 2018,"10:30", "11:30","location3", "description3");
        mCalendarEventDao.createCalendarEvent("Event4", 23, 4, 2018,"10:30", "11:30","location4", "description4");
    }


    public static void open(Context context) {
        if (database == null) {
            if (RESET_DATABASE_ON_OPEN)
                context.deleteDatabase(Database.DATABASE_NAME);
            database = new Database(context);

        } else {
            Log.e(TAG, "Cannot open database - database is already opened");
        }
    }

    public void close() {
        if (database != null) {
            mDatabaseHelper.close();
            database = null;
        } else {
            Log.e(TAG, "Cannot close database - database is not opened");
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ICalendarEventSchema.SQL_CREATE_TABLE_LIST_ITEM);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            deleteDatabase(db);
            onCreate(db);
        }

        public void deleteDatabase(SQLiteDatabase db) {
            db.execSQL(ICalendarEventSchema.SQL_DELETE_TABLE_CALENDAR_EVENT);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }
}
