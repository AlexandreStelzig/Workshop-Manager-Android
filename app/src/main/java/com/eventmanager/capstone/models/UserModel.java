package com.eventmanager.capstone.models;

public class UserModel {

    private int mDatabaseUserId;
    private String mUserId;
    private String mUsername;

    public UserModel(int databaseUserId, String userId, String username){
        this.mDatabaseUserId = databaseUserId;
        this.mUserId = userId;
        this.mUsername = username;
    }


    public int getDatabaseUserId() {
        return mDatabaseUserId;
    }

    public void setDatabaseUserId(int databaseUserId) {
        this.mDatabaseUserId = databaseUserId;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        this.mUserId = userId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }
}
