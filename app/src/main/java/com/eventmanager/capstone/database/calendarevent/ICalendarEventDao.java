package com.eventmanager.capstone.database.calendarevent;

import com.eventmanager.capstone.models.CalendarEventModel;

import java.util.List;

public interface ICalendarEventDao {

    CalendarEventModel fetchCalendarEventId(int calendarEventId);
    List<CalendarEventModel> fetchAllCalendarEvents();

    int createCalendarEvent(String name, int dayOfMonth, int month, int year, String startTime, String endTime, String location, String description);
    boolean deleteCalendarEvent(int calendarEventId);

}
