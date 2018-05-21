package com.eventmanager.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.eventmanager.capstone.database.Database;
import com.eventmanager.capstone.models.CalendarEventModel;

import java.text.DateFormatSymbols;

public class WorkshopActivity extends AppCompatActivity {


    private int mCalendarEventId;

    private TextView nameTextView;
    private TextView timeTextView;
    private TextView locationTextView;
    private TextView descriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);

        // intro animation
        // overridePendingTransition(R.anim.anim_enter_from_right, R.anim.anim_stay_idle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.activty_workshop_title);

        nameTextView = findViewById(R.id.workshop_name_text_view);
        timeTextView = findViewById(R.id.workshop_time_text_view);
        locationTextView = findViewById(R.id.workshop_location_text_view);
        descriptionTextView = findViewById(R.id.workshop_description_text_view);


        mCalendarEventId = getIntent().getIntExtra("EventId", -1);

        if(mCalendarEventId == -1){
            Toast.makeText(this, "Error while opening workshop", Toast.LENGTH_SHORT).show();
            Intent intentCancel = new Intent();
            setResult(Activity.RESULT_CANCELED, intentCancel);
            finish();
        }else{
            CalendarEventModel calendarEvent =
                    Database.mCalendarEventDao.fetchCalendarEventId(mCalendarEventId);

            nameTextView.setText(calendarEvent.getName());
            timeTextView.setText(calendarEvent.getStartTime() + " to " + calendarEvent.getEndTime() + ", "  + calendarEvent.getDayOfMonth() + " "
                    + new DateFormatSymbols().getMonths()[calendarEvent.getMonth()-1] + " " + calendarEvent.getYear());
            locationTextView.setText(calendarEvent.getLocation());
            descriptionTextView.setText(calendarEvent.getDescription());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_workshop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // toolbar back button pressed
        if (id == android.R.id.home) {
            Intent intentCancel = new Intent();
            setResult(Activity.RESULT_CANCELED, intentCancel);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
