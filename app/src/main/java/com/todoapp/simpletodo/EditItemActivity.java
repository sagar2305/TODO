package com.todoapp.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText editTextView;
    String textToEdit;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editTextView = (EditText) findViewById(R.id.editTextView);

        Intent intent = getIntent();
        if (intent.hasExtra("textToEdit")) {
            textToEdit = intent.getStringExtra("textToEdit");
            editTextView.setText(textToEdit);
            editTextView.setSelection(editTextView.getText().length());
        }
        if (intent.hasExtra("index")) {
            index = intent.getIntExtra("index", 0);
        }
    }

    public void onEditSaved(View v) {
        Intent data = new Intent();

        data.putExtra("item", editTextView.getText().toString());
        data.putExtra("index", index);

        setResult(RESULT_OK, data);
        finish();
    }
}
