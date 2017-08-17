package com.todoapp.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.todoapp.simpletodo.Constants.EDITEDITEM;
import static com.todoapp.simpletodo.Constants.INDEX;
import static com.todoapp.simpletodo.Constants.ITEMTOEDIT;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 20;

    ArrayList<Task> tasks;
    TasksAdapter tasksAdapter;
    ListView lvlItems;
    DatabaseHelper db;

    static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvlItems = (ListView)findViewById(R.id.lvlItems);
        db = new DatabaseHelper(this);

        //add existing items
        tasks = tasksFromDB();

        tasksAdapter = new TasksAdapter(this, tasks);
        lvlItems.setAdapter(tasksAdapter);

        //setup list view listener
        setupListViewListener();
    }

    //handle add item button click
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);

        String itemText = etNewItem.getText().toString();
        Task task = new Task(counter++, itemText);

        tasksAdapter.add(task);
        etNewItem.setText("");
        writeItem(task);
    }

    private void setupListViewListener() {
        lvlItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = tasks.get(i);
                tasks.remove(task);
                db.deleteTask(task);

                tasksAdapter.notifyDataSetChanged();
                return true;
            }
        });

        lvlItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra(ITEMTOEDIT, tasks.get(i));
                intent.putExtra(INDEX, i);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Task task = data.getExtras().getParcelable(EDITEDITEM);
            int index = data.getExtras().getInt(INDEX, -1);

            if (index >= 0 && tasks.size() > index) {
                tasks.set(index, task);
            }
            tasksAdapter.notifyDataSetChanged();
            db.updateTask(task);
        }
    }

    //read items from DB
    private ArrayList<Task> tasksFromDB() {
        ArrayList<Task> tasks = db.getAllTasks();
        return tasks;
    }

    //write item to DB
    private void writeItem(Task task) {
        db.addTask(task);
    }
}
