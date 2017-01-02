package pl.matczakonline.zaliczeniowa.common.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.matczakonline.zaliczeniowa.common.helpers.LoginDataBaseAdapter;
import pl.matczakonline.zaliczeniowa.R;

public class HomeActivity extends Activity {
    Button btnSignIn;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void todoApp() {
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

        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

        if (password.equals(storedPassword)) {
            todoApp();
            Toast.makeText(HomeActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HomeActivity.this, "Username or Password does not match", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}
