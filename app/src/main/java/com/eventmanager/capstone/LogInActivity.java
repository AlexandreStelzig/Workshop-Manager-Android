package com.eventmanager.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.eventmanager.capstone.api.APIManager;
import com.eventmanager.capstone.api.VolleyCallback;
import com.eventmanager.capstone.database.Database;
import com.eventmanager.capstone.utilities.ToastManager;


public class LogInActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Database.open(this);

        if(Database.mUserDao.fetchActiveUser() != null){
            Intent i = new Intent(LogInActivity.this, CalendarActivity.class);
            startActivity(i);
            finish();
        }else{
            initiateLoginActivity();
        }



    }

    private void initiateLoginActivity() {

        setContentView(R.layout.activity_log_in);

        mUsernameEditText = (EditText) findViewById(R.id.activity_log_in_user_name_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.activity_log_in_password_edit_text);
        mLoginButton = (Button) findViewById(R.id.activity_log_in_button);
        mProgressBar = (ProgressBar) findViewById(R.id.activity_log_in_progress_bar);


        final VolleyCallback volleyCallback = new VolleyCallback() {
            @Override
            public void onResponse(boolean success) {
                if(success){
                    loginUser();
                }else{
                    ToastManager.showAToast(LogInActivity.this, LogInActivity.this.getString(R.string.unsuccessfulLoginMessage));
                    setLoginButtonVisibility(true);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                ToastManager.showAToast(LogInActivity.this, "Network error");
                setLoginButtonVisibility(true);
            }
        };

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                APIManager.getInstance(LogInActivity.this).callLoginAPI(volleyCallback, mUsernameEditText.getText().toString(), mPasswordEditText.getText().toString());
                setLoginButtonVisibility(false);


            }
        });

        setLoginButtonVisibility(true);
    }

    private void setLoginButtonVisibility(boolean visible){
        if(visible){
            mLoginButton.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }else{
            mProgressBar.setVisibility(View.VISIBLE);
            mLoginButton.setVisibility(View.GONE);
        }


    }

    public void loginUser(){

        // todo fetch real user name and user id
        Database.mUserDao.setActiveUser("Placeholder Name", mUsernameEditText.getText().toString());

        Intent i = new Intent(LogInActivity.this, CalendarActivity.class);
        startActivity(i);
        finish();
    }
}
