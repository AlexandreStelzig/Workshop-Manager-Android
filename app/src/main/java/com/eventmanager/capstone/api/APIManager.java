package com.eventmanager.capstone.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
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

    private static final String ERR_UNKNOWN_STATUS_CODE = "The application has encountered an unknown error.";
    private static final String ERR_GENERIC = "Server Error";

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
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("TEST", "Response: " + response.toString());
                            callback.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onErrorResponse(error);
                        }
                    });
            // Add the request to the RequestQueue.
            APIQueueManager.getInstance(mContext).addToRequestQueue(jsonObjectRequest);
        } catch (JSONException e) {
            Log.d("TEST", "Throw exception");
            e.printStackTrace();
        }
    }

    public static String handleServerError(Object err, Context context) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        if (response != null) {
            switch (response.statusCode) {
                case 404:
                case 422:
                case 400:
                case 401:

                    try {
                        String string = new String(error.networkResponse.data);
                        JSONObject object = new JSONObject(string);
                        if (object.has("message")) {
                            return object.get("message").toString();
                        }
                        else if(object.has("error_description")) {
                            return object.get("error_description").toString();
                        } else if(object.has("status")) {
                            return object.get("status").toString();
                        }
                    }catch (JSONException e)
                    {
                        return "Could not parse response";
                    }
                    // invalid request
                    return error.getMessage();

                default:
                    return ERR_UNKNOWN_STATUS_CODE;
            }
        }
        return ERR_GENERIC;
    }

}
