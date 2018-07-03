package com.eventmanager.capstone.api;

import com.android.volley.VolleyError;

public interface VolleyCallback {
    void onResponse(boolean success);
    void onErrorResponse(VolleyError error);
}
