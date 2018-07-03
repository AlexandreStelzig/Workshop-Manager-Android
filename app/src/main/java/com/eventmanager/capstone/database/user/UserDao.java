package com.eventmanager.capstone.database.user;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eventmanager.capstone.database.DbContentProvider;
import com.eventmanager.capstone.models.CalendarEventModel;
import com.eventmanager.capstone.models.UserModel;

public class UserDao extends DbContentProvider implements  IUserSchema, IUserDao{

    public UserDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public UserModel fetchActiveUser() {
        UserModel user = null;
        Cursor cursor = super.rawQuery("SELECT * FROM " + USER_TABLE, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                user = cursorToEntity(cursor);
            }
            cursor.close();
        }

        return user;
    }

    @Override
    public int setActiveUser(String username, String userId) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_NAME, username);
        values.put(COLUMN_USER_ID, userId);

        try {
            return super.insert(USER_TABLE, values);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
            return -1;
        }
    }

    @Override
    public boolean removeActiveUser() {

        return super.delete(USER_TABLE, COLUMN_DATABASE_USER_ID + "=" + fetchActiveUser().getDatabaseUserId(), null) > 0;
    }


    @Override
    protected UserModel cursorToEntity(Cursor cursor) {
        int userDatabaseId = cursor.getInt(cursor.getColumnIndex(COLUMN_DATABASE_USER_ID));
        String userId = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID));
        String name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));



        return new UserModel(userDatabaseId, userId, name);
    }


}
