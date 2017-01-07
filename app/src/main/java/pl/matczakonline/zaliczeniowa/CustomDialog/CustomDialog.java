package pl.matczakonline.zaliczeniowa.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.matczakonline.zaliczeniowa.R;
import pl.matczakonline.zaliczeniowa.common.activities.AddTaskActivity;
import pl.matczakonline.zaliczeniowa.common.activities.EditTaskActivity;

/**
 * Created by michnik on 07.01.2017.
 */

public class CustomDialog extends Dialog {

    private String[] priorities = new String[]{"High", "Normal", "Low"};

    public CustomDialog(Context context, String title, String description, int priority) {
        super(context);
        setContentView(R.layout.edit_task_modal);
        setTextViewBoxes(title, description, priority);
        setButtonsListeners();
    }

    private void setButtonsListeners() {
        Button cancelButton = (Button) findViewById(R.id.dismiss_button);
        Button editButton = (Button) findViewById(R.id.edit_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEditTask = new Intent(getContext(), EditTaskActivity.class);
                getContext().startActivity(intentEditTask);
            }
        });
    }

    private void setTextViewBoxes(String title, String description, int priority) {
        TextView _title = (TextView) findViewById(R.id.title);
        TextView _description = (TextView) findViewById(R.id.description);
        TextView _priority = (TextView) findViewById(R.id.priority);

        _title.setText(title);
        _description.setText(description);
        _priority.setText(priorities[priority]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
