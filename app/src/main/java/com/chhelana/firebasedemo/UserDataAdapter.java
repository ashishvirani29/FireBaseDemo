package com.chhelana.firebasedemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Veera Developer on 1/2/2018.
 */

public class UserDataAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<UserData> userData;

    public UserDataAdapter(MainActivity mainActivity, ArrayList<UserData> userDatas) {
        this.activity = mainActivity;
        this.userData = userDatas;
    }

    @Override
    public int getCount() {
        return userData.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView question;
        TextView answer;
        TextView email;
        TextView password;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.question = (TextView) convertView.findViewById(R.id.question);
            holder.answer = (TextView) convertView.findViewById(R.id.ans);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.password = (TextView) convertView.findViewById(R.id.password);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.question.setText(userData.get(position).getQuestion());
        holder.answer.setText(userData.get(position).getAns());
        holder.email.setText(userData.get(position).getEmail());
        holder.password.setText(userData.get(position).getPassword());


        return convertView;
    }
}
