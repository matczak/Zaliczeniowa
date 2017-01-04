package pl.matczakonline.zaliczeniowa.common.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.w3c.dom.Text;

import java.util.List;

import pl.matczakonline.zaliczeniowa.CustomListAdapter.CustomListAdapter;
import pl.matczakonline.zaliczeniowa.R;
import pl.matczakonline.zaliczeniowa.common.db.DatabaseHelper;
import pl.matczakonline.zaliczeniowa.common.db.Todo;
import pl.matczakonline.zaliczeniowa.floatingbutton.FloatingActionButton;

/**
 * Created by michn on 29.12.2016.
 */

public class TodoAppActivity extends Activity {
    DatabaseHelper dbHelper;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.todo_app);
        this.initToolbar();
        this.initAddButton();
        list = (ListView) findViewById(R.id.list);
    }

    private void initAddButton() {
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addTask();
            }
        });
    }

    private void initToolbar() {
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Matczak");
        mActionBarToolbar.setTitleTextColor(Color.WHITE);
    }

    public void addTask() {
        Intent intentAddTask = new Intent(getApplicationContext(), AddTaskActivity.class);
        startActivity(intentAddTask);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseHelper dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Todo, Integer> todoDao = dbHelper.getTodoRuntimeExceptionDao();
        List<Todo> todos = todoDao.queryForAll();

        CustomListAdapter aa = new CustomListAdapter(getApplicationContext(), R.layout.list_view_0, todos);
        list.setAdapter(aa);
    }
}

