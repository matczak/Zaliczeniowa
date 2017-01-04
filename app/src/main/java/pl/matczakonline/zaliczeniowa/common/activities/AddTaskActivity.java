package pl.matczakonline.zaliczeniowa.common.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import pl.matczakonline.zaliczeniowa.R;
import pl.matczakonline.zaliczeniowa.common.db.DatabaseHelper;
import pl.matczakonline.zaliczeniowa.common.db.Todo;

/**
 * Created by michnik on 02.01.2017.
 */

public class AddTaskActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        initSpinner();
        initAddButton();
    }

    private void initAddButton() {
        Button addButton = (Button) findViewById(R.id.addTaksBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaskToDb();
            }
        });
    }

    private void addTaskToDb() {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Todo, Integer> todoDao = dbHelper.getTodoRuntimeExceptionDao();

        int selectedPriority = getSelectedPriority();
        String title = ((EditText) findViewById(R.id.title)).getText().toString();
        String description = ((EditText) findViewById(R.id.description)).getText().toString();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(AddTaskActivity.this, "Title and description can not be empty!", Toast.LENGTH_LONG).show();
        } else {
            todoDao.create(new Todo(title, description, selectedPriority));
            finish();
        }
    }


    private int getSelectedPriority() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        return spinner.getSelectedItemPosition();
    }

    private void initSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


//    dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
//    RuntimeExceptionDao<Todo, Integer> todoDao = dbHelper.getTodoRuntimeExceptionDao();
//
//
//    todoDao.create(new Todo("title 1", "desc 1", 1));
//    todoDao.create(new Todo("title 2", "desc 2", 1));
//    todoDao.create(new Todo("title 3", "desc 3", 3));

}
