package ntheurer.dodatesapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.*;

import ntheurer.dodatesapp.R;

public class LoginActivity extends AppCompatActivity {

    private final String tag = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(tag, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.usernameEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            @Override
            public void onClick(View v) {
                Log.w(tag, "registerButton clicked");
                Intent myIntent = new Intent(LoginActivity.this, CalendarActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            @Override
            public void onClick(View v) {
                Log.w(tag, "loginButton clicked");
                Intent myIntent = new Intent(LoginActivity.this, CalendarActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
