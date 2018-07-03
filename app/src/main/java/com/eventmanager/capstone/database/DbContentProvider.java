package com.eventmanager.capstone.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alex on 2018-03-07.
 */

public abstract class DbContentProvider {
    public SQLiteDatabase mDb;


    public DbContentProvider(SQLiteDatabase db) {
        this.mDb = db;
    }


    public int delete(String tableName, String selection,
                      String[] selectionArgs) {
        return mDb.delete(tableName, selection, selectionArgs);
    }

    public int insert(String tableName, ContentValues values) {
        return (int) mDb.insert(tableName, null, values);
    }

    public int update(String tableName, ContentValues values,
                      String selection, String[] selectionArgs) {
        return mDb.update(tableName, values, selection,
                selectionArgs);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return mDb.rawQuery(sql, selectionArgs);
    }

    public Cursor rawQuery(String query) {
        return mDb.rawQuery(query, null);
    }

    protected abstract <T> T cursorToEntity(Cursor cursor);
}
