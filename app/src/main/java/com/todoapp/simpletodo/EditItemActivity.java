package com.todoapp.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.todoapp.simpletodo.Constants.EDITEDITEM;
import static com.todoapp.simpletodo.Constants.INDEX;
import static com.todoapp.simpletodo.Constants.ITEMTOEDIT;

public class EditItemActivity extends AppCompatActivity {

    EditText editTextView;
    Task task;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editTextView = (EditText) findViewById(R.id.editTextView);
        editTextView.setText("");

        Intent intent = getIntent();
        if (intent.hasExtra(ITEMTOEDIT)) {
            task = intent.getParcelableExtra(ITEMTOEDIT);
            editTextView.setText(task.name);
            editTextView.setSelection(editTextView.getText().length());
        }
        if (intent.hasExtra(INDEX)) {
            index = intent.getIntExtra(INDEX, -1);
        }
    }

    //save the edited item
    public void onEditSaved(View v) {
        Intent data = new Intent();

        task.name = editTextView.getText().toString();

        data.putExtra(EDITEDITEM, task);
        data.putExtra(INDEX, index);

        setResult(RESULT_OK, data);
        finish();
    }
}
