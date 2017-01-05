package pl.matczakonline.zaliczeniowa.common.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import pl.matczakonline.zaliczeniowa.R;
import pl.matczakonline.zaliczeniowa.common.db.DatabaseHelper;
import pl.matczakonline.zaliczeniowa.common.db.User;

public class SignUPActivity extends Activity {
    Button btnCreateAccount;
    RuntimeExceptionDao<User, Integer> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        setRuntimeExceptionDaoForUsers();


        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        EditText editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        } else {
            User user = new User(userName, password);
            users.create(user);
            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            todoApp(user);
        }
    }

    private void setRuntimeExceptionDaoForUsers() {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        users = dbHelper.getUserRuntimeExceptionDao();
    }

    private void todoApp(User user) {
        SharedPreferences settings = getSharedPreferences("User", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("userID", user.getId());
        editor.commit();

        Intent intentTodoApp = new Intent(getApplicationContext(), TodoAppActivity.class);
        startActivity(intentTodoApp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
