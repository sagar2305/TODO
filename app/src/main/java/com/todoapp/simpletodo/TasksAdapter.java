package com.todoapp.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by SagarMutha on 8/13/17.
 */

public class TasksAdapter extends ArrayAdapter implements CompoundButton.OnCheckedChangeListener {

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
        TextView dueDateTextView = (TextView) convertView.findViewById(R.id.tvDueDate);

        CheckBox checkBoxTaskComplete = (CheckBox) convertView.findViewById(R.id.cbTaskComplete);
        checkBoxTaskComplete.setTag(Integer.valueOf(position));
        checkBoxTaskComplete.setOnCheckedChangeListener(this);

        nameTextView.setText(task.getName());
        if (task.isCompleted()) {
            nameTextView.setPaintFlags(nameTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            checkBoxTaskComplete.setChecked(true);
        } else {
            nameTextView.setPaintFlags(nameTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            checkBoxTaskComplete.setChecked(false);
        }

        dueDateTextView.setText("");
        if (task.getDueDate() != null) {
            String myFormat = "EEE, MMM d";

            boolean pastDue = task.getDueDate().before(Calendar.getInstance().getTime());
            int color = pastDue && !task.isCompleted() ? Color.RED : Color.BLUE;
            dueDateTextView.setTextColor(color);

            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            dueDateTextView.setText(sdf.format(task.getDueDate()));
        }

        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        Task task = (Task) getItem((Integer)compoundButton.getTag());
        task.setCompleted(isChecked);
        notifyDataSetChanged();
        ((MainActivity)getContext()).updateTask(task);
    }
}
