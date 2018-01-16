package com.eventmanager.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CalendarActivity extends AppCompatActivity {


    // Activity Result Codes
    public final static int PROFILE_RESULT_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ((Button) findViewById(R.id.activity_calendar_open_workshop_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openProfileIntent = new Intent(CalendarActivity.this, WorkshopActivity.class);
                startActivity(openProfileIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_profile) {
            Intent openProfileIntent = new Intent(this, ProfileActivity.class);
            startActivityForResult(openProfileIntent, PROFILE_RESULT_RESULT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PROFILE_RESULT_RESULT){
            // animation when coming back from activity profile
            overridePendingTransition(R.anim.anim_stay_idle, R.anim.anim_exit_to_right);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
