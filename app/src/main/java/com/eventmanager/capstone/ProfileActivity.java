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

import com.eventmanager.capstone.adapters.ProfileListViewAdapter;
import com.eventmanager.capstone.adapters.ProfileLogOffListViewAdapter;
import com.eventmanager.capstone.utilities.ListViewUtilities;

public class ProfileActivity extends AppCompatActivity {


    private ListView optionsListView;
    private ListView logOffListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // intro animation
        overridePendingTransition(R.anim.anim_enter_from_right, R.anim.anim_stay_idle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.activity_profile_title);


        optionsListView = (ListView) findViewById(R.id.profile_options_listview);
        logOffListView = (ListView) findViewById(R.id.profile_log_off_list_view);

        initOptionsListView();
        initLogOffListView();
    }

    private void initOptionsListView() {
        final String[] moreOptionsStrings = getResources().getStringArray(R.array.profile_listview_options);


        int[] drawableIds = {R.drawable.ic_settings_black_24dp, R.drawable.ic_feedback_black_24dp, R.drawable.ic_info_black_24dp};

        ProfileListViewAdapter adapter = new ProfileListViewAdapter(this, this, moreOptionsStrings, drawableIds);

        optionsListView.setAdapter(adapter);
        optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    // settings
                    case 0:

                        break;
                    // send feedback
                    case 1:

                        break;
                    // about
                    case 2:

                        break;
                }

            }
        });

        ListViewUtilities.setListViewHeightBasedOnChildren(optionsListView);

    }

    private void initLogOffListView() {
        final String[] changeProfileOptions = getResources().getStringArray(R.array.profile_log_off);

        ProfileLogOffListViewAdapter adapter = new ProfileLogOffListViewAdapter(this, this, changeProfileOptions);

        logOffListView.setAdapter(adapter);
        logOffListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    // log off
                    case 0:
                        Intent i = new Intent(ProfileActivity.this, LogInActivity.class);
                        startActivity(i);
                        finish();
                        break;
                }

            }
        });

        ListViewUtilities.setListViewHeightBasedOnChildren(logOffListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
