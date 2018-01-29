package com.eventmanager.capstone;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.eventmanager.capstone.models.CalendarEventModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private enum WEEK_VIEW_TYPE{
        DAY,
        THREE_DAYS,
        WEEK
    }

    // Activity Result Codes
    public final static int PROFILE_RESULT_RESULT = 1;


    private WeekView mWeekView;
    private List<CalendarEventModel> calendarEventModelList;

    private WEEK_VIEW_TYPE currentWeekViewType = WEEK_VIEW_TYPE.WEEK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {
                Intent openProfileIntent = new Intent(CalendarActivity.this, WorkshopActivity.class);
                startActivity(openProfileIntent);
            }
        });


        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

                // Populate the week view with some events.
                List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
                Log.d("TEST", "new");
                for (CalendarEventModel calendarEventModel : calendarEventModelList) {
                    WeekViewEvent weekViewEvent = calendarEventModel.toWeekViewEvent();
                    Log.d("TEST", "added");

                    if (eventMatches(weekViewEvent, newYear, newMonth)) {
                        events.add(weekViewEvent);
                    }
                }


                return events;
            }
        });

        mWeekView.setEventLongPressListener(new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
                Toast.makeText(CalendarActivity.this, getEventTitle(event.getName(), event.getStartTime(), event.getEndTime()), Toast.LENGTH_SHORT).show();
            }
        });

        initializeCalendarEvent();
        mWeekView.goToHour(8);
        setupDateTimeInterpreter(true);
    }


    protected String getEventTitle(String name, Calendar startTime, Calendar endTime) {
        return String.format(name + " from %02d:%02d to %02d:%02d, %s/%d", startTime.get(Calendar.HOUR_OF_DAY), startTime.get(Calendar.MINUTE), endTime.get(Calendar.HOUR_OF_DAY), endTime.get(Calendar.MINUTE), startTime.get(Calendar.MONTH) + 1, startTime.get(Calendar.DAY_OF_MONTH));
    }

    private void initializeCalendarEvent() {
        calendarEventModelList = new ArrayList<>();

        calendarEventModelList.add(new CalendarEventModel("Event 1", 29, 0, "8:00", "9:30", ContextCompat.getColor(this, R.color.colorPrimary)));
        calendarEventModelList.add(new CalendarEventModel("Event 2", 29, 0, "11:30", "13:25", ContextCompat.getColor(this, R.color.colorPrimary)));
        calendarEventModelList.add(new CalendarEventModel("Event 3", 31, 0, "11:30", "13:25", ContextCompat.getColor(this, R.color.colorPrimary)));
        calendarEventModelList.add(new CalendarEventModel("Event 4", 1, 1, "16:20", "17:20", ContextCompat.getColor(this, R.color.colorPrimary)));

    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        Calendar firstVisibleDay = mWeekView.getFirstVisibleDay();
        double firstVisibleHour = mWeekView.getFirstVisibleHour();

        switch (id) {
            case R.id.action_profile:
                Intent openProfileIntent = new Intent(this, ProfileActivity.class);
                startActivityForResult(openProfileIntent, PROFILE_RESULT_RESULT);
                return true;
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (currentWeekViewType != WEEK_VIEW_TYPE.DAY) {
                    item.setChecked(!item.isChecked());
                    currentWeekViewType = WEEK_VIEW_TYPE.DAY;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    setupDateTimeInterpreter(false);
                    mWeekView.goToDate(firstVisibleDay);
                    mWeekView.goToHour(firstVisibleHour);
                }
                return true;
            case R.id.action_three_day_view:
                if (currentWeekViewType != WEEK_VIEW_TYPE.THREE_DAYS) {
                    item.setChecked(!item.isChecked());
                    currentWeekViewType = WEEK_VIEW_TYPE.THREE_DAYS;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    setupDateTimeInterpreter(false);
                    mWeekView.goToDate(firstVisibleDay);
                    mWeekView.goToHour(firstVisibleHour);
                }
                return true;
            case R.id.action_week_view:
                if (currentWeekViewType != WEEK_VIEW_TYPE.WEEK) {
                    item.setChecked(!item.isChecked());
                    currentWeekViewType = WEEK_VIEW_TYPE.WEEK;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    setupDateTimeInterpreter(true);
                    mWeekView.goToDate(firstVisibleDay);
                    mWeekView.goToHour(firstVisibleHour);
                }
                return true;


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PROFILE_RESULT_RESULT) {
            // animation when coming back from activity profile
            overridePendingTransition(R.anim.anim_stay_idle, R.anim.anim_exit_to_right);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d/M", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }
}
