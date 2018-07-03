package com.eventmanager.capstone.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eventmanager.capstone.LogInActivity;
import com.eventmanager.capstone.R;
import com.eventmanager.capstone.utilities.ToastManager;

import org.json.JSONException;
import org.json.JSONObject;

public class APIManager {

    // use 10.0.2.2 for emulator (localhost)
    // if you want to use your device, makes sure that it is connected to the same network
    // also change the address to your computer ip (not public ip - do ipconfig - under inet)
//    private static final String API_IP = "http://192.168.0.10:3000/";
    private static final String API_IP = "http://10.0.2.2:3000/";

    private static APIManager mInstance;
    private Context mContext;

    private APIManager(Context context) {
        mContext = context;
    }

    public static synchronized APIManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new APIManager(context);
        }
        return mInstance;
    }

    public void callLoginAPI(final VolleyCallback callback, String username, String password){

        try {
            String url = API_IP + "auth/validatecredentials";
            JSONObject jsonBody;
            jsonBody = new JSONObject();
            jsonBody.put("username", username);
            jsonBody.put("password", password);
            String requestBody = jsonBody.toString();
            BooleanRequest booleanRequest = new BooleanRequest(Request.Method.POST, url, requestBody,
                    new Response.Listener<Boolean>() {
                        @Override
                        public void onResponse(Boolean response) {
                                callback.onResponse(response);
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // todo filter errors and give appropriate response to use (e.g. no internet, missing permissions...)
                    callback.onErrorResponse(error);
                }
            });
            // Add the request to the RequestQueue.
            APIQueueManager.getInstance(mContext).addToRequestQueue(booleanRequest);
        } catch (JSONException e) {
            Log.d("TEST", "Throw exception");
            e.printStackTrace();
        }
    }

}
