package com.todoapp.simpletodo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.todoapp.simpletodo.Constants.EDITEDITEM;
import static com.todoapp.simpletodo.Constants.INDEX;
import static com.todoapp.simpletodo.Constants.ITEMTOEDIT;

public class EditItemActivity extends AppCompatActivity {

    EditText editTextView;
    Task task;
    int index = -1;
    Calendar myCalendar;
    EditText dueDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        dueDateText = (EditText) findViewById(R.id.etDueDate);
        editTextView = (EditText) findViewById(R.id.editTextView);

        editTextView.setText("");

        Intent intent = getIntent();
        if (intent.hasExtra(ITEMTOEDIT)) {
            task = intent.getParcelableExtra(ITEMTOEDIT);

            editTextView.setText(task.getName());
            editTextView.setSelection(editTextView.getText().length());
            updateDueDateLabel();
        }
        if (intent.hasExtra(INDEX)) {
            index = intent.getIntExtra(INDEX, -1);
        }

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
             @Override
             public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                 task.setDueDate(myCalendar.getTime());
                 updateDueDateLabel();
            }

        };

        dueDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditItemActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateDueDateLabel() {
        Date dueDate = task.getDueDate();
        if (dueDate != null) {
            int color = dueDate.before(Calendar.getInstance().getTime()) ? Color.RED : Color.BLUE;
            dueDateText.setTextColor(color);

            String myFormat = "EEE, MMM d";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            dueDateText.setText("Due "+ sdf.format(dueDate));
        }
    }

    //save the edited item
    public void onEditSaved(View v) {
        Intent data = new Intent();

        task.setName(editTextView.getText().toString());
      
        data.putExtra(EDITEDITEM, task);
        data.putExtra(INDEX, index);

        setResult(RESULT_OK, data);
        finish();
    }
}
