package pl.matczakonline.zaliczeniowa;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by michn on 29.12.2016.
 */

public class TodoAppActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.todo_app);
        this.initToolbar();

    }

    private void initToolbar() {
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Testy");
        mActionBarToolbar.setTitleTextColor(Color.WHITE);
    }
}

