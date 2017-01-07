package pl.matczakonline.zaliczeniowa.common.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
 * Created by michnik on 07.01.2017.
 */

public class EditTaskActivity extends Activity implements AdapterView.OnItemSelectedListener {

    EditText titleEditText;
    EditText descriptionEditText;
    Spinner spinner;

    RuntimeExceptionDao<Todo, Integer> todoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        initDb();
        initSpinner();
        initButtons();
        initToolbar();
        initEditTexts();
    }

    private void initDb() {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        todoDao = dbHelper.getTodoRuntimeExceptionDao();
    }

    private void initEditTexts() {
        titleEditText = (EditText) findViewById(R.id.title);
        descriptionEditText = (EditText) findViewById(R.id.description);

        Log.d("TaskID", Integer.toString(getIntent().getExtras().getInt("TodoID",0)));
        Todo todo = todoDao.queryForId(getIntent().getExtras().getInt("TodoID",0));

        titleEditText.setText(todo.getTitle());
        descriptionEditText.setText(todo.getDescription());
        spinner.setSelection(todo.getPriority());
    }

    private void initButtons() {
        Button addButton = (Button) findViewById(R.id.addTaksBtn);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaskToDb();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initToolbar() {
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Matczak");
        mActionBarToolbar.setTitleTextColor(Color.WHITE);
    }

    private void addTaskToDb() {

        int selectedPriority = getSelectedPriority();
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(EditTaskActivity.this, "Title and description can not be empty!", Toast.LENGTH_LONG).show();
        } else {
            int todoID = getIntent().getExtras().getInt("TodoID",0);
            Todo todo = todoDao.queryForId(todoID);
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setPriority(selectedPriority);
            todoDao.update(todo);
            finish();
        }
    }


    private int getSelectedPriority() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        return spinner.getSelectedItemPosition();
    }

    private void initSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
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
}
