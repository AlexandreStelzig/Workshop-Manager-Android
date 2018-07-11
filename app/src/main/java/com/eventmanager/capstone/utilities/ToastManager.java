package com.eventmanager.capstone.utilities;

import android.content.Context;
import android.widget.Toast;

import com.eventmanager.capstone.api.APIManager;

public class ToastManager {

    private static Toast mToast;

    public static void showAToast (Context context, String message){

        if(mToast == null){
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

        }

        mToast.setText(message);
        mToast.show();  //finally display it
    }

}
