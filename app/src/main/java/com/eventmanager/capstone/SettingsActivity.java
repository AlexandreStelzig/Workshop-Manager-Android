package com.eventmanager.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.eventmanager.capstone.adapters.ProfileListViewAdapter;
import com.eventmanager.capstone.adapters.ProfileLogOffListViewAdapter;
import com.eventmanager.capstone.database.Database;
import com.eventmanager.capstone.models.UserModel;
import com.eventmanager.capstone.utilities.ListViewUtilities;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // intro animation
        overridePendingTransition(R.anim.anim_enter_from_right, R.anim.anim_stay_idle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.activity_settings_title);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
