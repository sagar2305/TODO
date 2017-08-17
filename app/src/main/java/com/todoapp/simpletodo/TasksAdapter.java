package com.todoapp.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SagarMutha on 8/13/17.
 */

public class TasksAdapter extends ArrayAdapter {

    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the task
        Task task = (Task) getItem(position);

        // Check if view exists else inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_task, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.tvTask);
        nameTextView.setText(task.name);

        return convertView;
    }

}
