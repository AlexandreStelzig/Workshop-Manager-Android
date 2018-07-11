package com.eventmanager.capstone.database.user;

/**
 * Created by alex on 2018-03-12.
 */

public interface IUserSchema {

    // COLUMNS NAME
    String USER_TABLE = "user_table";
    String COLUMN_DATABASE_USER_ID = "database_user_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_USER_NAME = "user_name";


    // ON CREATE
    String SQL_CREATE_TABLE_USER = "CREATE TABLE "
            + USER_TABLE + " ("
            + COLUMN_DATABASE_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_ID + " TEXT,"
            + COLUMN_USER_NAME + " TEXT" + ");";

    // ON DELETE
    String SQL_DELETE_TABLE_USER = "DROP TABLE IF EXISTS " + USER_TABLE;
}
