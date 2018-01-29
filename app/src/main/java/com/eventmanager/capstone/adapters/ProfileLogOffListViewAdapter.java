package com.eventmanager.capstone.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eventmanager.capstone.R;

/**
 * Created by alex on 12/29/2017.
 */

public class ProfileLogOffListViewAdapter extends BaseAdapter {


    private Context mContext;
    private Activity mActivity;
    private String[] titles;

    public ProfileLogOffListViewAdapter(Context context, Activity activity, String[] textlist) {
        mContext = context;
        mActivity = activity;
        titles = textlist;

    }

    public int getCount() {
        return titles.length;
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View row;
        row = inflater.inflate(R.layout.custom_profile_log_off_listview_item, parent, false);
        TextView title;
        title = (TextView) row.findViewById(R.id.custom_profile_log_off_listview_item_text);
        title.setText(titles[position]);

        return (row);
    }
}