package pl.matczakonline.zaliczeniowa.common.activities;

import android.app.Activity;
import android.os.Bundle;

import pl.matczakonline.zaliczeniowa.R;

/**
 * Created by michnik on 02.01.2017.
 */

public class AddTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
    }
}
