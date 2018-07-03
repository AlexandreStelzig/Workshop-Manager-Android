package com.eventmanager.capstone.database.user;

import com.eventmanager.capstone.models.CalendarEventModel;
import com.eventmanager.capstone.models.UserModel;

import java.util.List;

public interface IUserDao {

    UserModel fetchActiveUser();

    int setActiveUser(String username, String userId);
    boolean removeActiveUser();



}
