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

public class ProfileListViewAdapter extends BaseAdapter {


    private Context mContext;
    private Activity mActivity;
    private String[] titles;
    private int[] images;

    public ProfileListViewAdapter(Context context, Activity activity, String[] textlist, int[] imageIds) {
        mContext = context;
        mActivity = activity;
        titles = textlist;
        images = imageIds;

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
        row = inflater.inflate(R.layout.custom_profile_listview_item, parent, false);
        TextView title;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.custom_profile_listview_item_icon);
        title = (TextView) row.findViewById(R.id.custom_profile_listview_item_text);
        title.setText(titles[position]);
        i1.setImageResource(images[position]);

        return (row);
    }
}