package pl.matczakonline.zaliczeniowa.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.matczakonline.zaliczeniowa.R;
import pl.matczakonline.zaliczeniowa.common.activities.EditTaskActivity;
import pl.matczakonline.zaliczeniowa.common.db.Todo;

/**
 * Created by michnik on 07.01.2017.
 */

public class CustomDialog extends Dialog {

    private String[] priorities = new String[]{"High", "Normal", "Low"};
    private Todo todo;
    private Context dialogContext;

    public CustomDialog(Context context, Todo todo) {
        super(context);
        setContentView(R.layout.edit_task_modal);
        setTextViewBoxes(todo);
        setButtonsListeners();
        this.todo = todo;
        this.dialogContext = context;
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
                intentEditTask.putExtra("TodoID", todo.getId());
                getContext().startActivity(intentEditTask);
                dismiss();
            }
        });
    }

    private void setTextViewBoxes(Todo todo) {
        TextView _title = (TextView) findViewById(R.id.title);
        TextView _description = (TextView) findViewById(R.id.description);
        TextView _priority = (TextView) findViewById(R.id.priority);

        _title.setText(todo.getTitle());
        _description.setText(todo.getDescription());
        _priority.setText(priorities[todo.getPriority()]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
