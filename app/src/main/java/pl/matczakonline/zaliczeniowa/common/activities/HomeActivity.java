package pl.matczakonline.zaliczeniowa.common.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import pl.matczakonline.zaliczeniowa.R;
import pl.matczakonline.zaliczeniowa.common.db.DatabaseHelper;
import pl.matczakonline.zaliczeniowa.common.db.User;

public class HomeActivity extends Activity {
    Button btnSignIn;
    RuntimeExceptionDao<User, Integer> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        DatabaseHelper dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        users = dbHelper.getUserRuntimeExceptionDao();

        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void todoApp(User user) {
        SharedPreferences settings = getSharedPreferences("User", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("userID", user.getId());
        editor.commit();

        Intent intentTodoApp = new Intent(getApplicationContext(), TodoAppActivity.class);
        startActivity(intentTodoApp);
    }

    public void signUp(View V) {
        Intent intentSignUP = new Intent(getApplicationContext(), SignUPActivity.class);
        startActivity(intentSignUP);
    }

    private void signIn() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        final EditText editTextUserName = (EditText) findViewById(R.id.editTextUsername);
        final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

        int userId = getUserIdByUserName(userName);
        if (userId == -1) {
            Toast.makeText(HomeActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
        } else {
            User user = users.queryForId(userId);
            String storedPassword = user.getPassword();

            if (password.equals(storedPassword)) {
                todoApp(user);
                Toast.makeText(HomeActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(HomeActivity.this, "Username or Password does not match", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int getUserIdByUserName(String userName) {
        List<User> _users = users.queryForEq("userName", userName);
        if (!_users.isEmpty()) {
            return _users.get(0).getId();
        }
        return -1;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
