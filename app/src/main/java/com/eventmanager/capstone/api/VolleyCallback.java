package com.eventmanager.capstone.api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyCallback {
    void onResponse(JSONObject jsonObject);
    void onErrorResponse(VolleyError error);
}
