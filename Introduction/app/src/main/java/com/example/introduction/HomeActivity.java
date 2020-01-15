package com.example.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.introduction.model.User;

public class HomeActivity extends AppCompatActivity {

    // This object will hold our xml view
    private TextView tvGreeting;
    private TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // assign the object with findViewById
        tvGreeting = findViewById(R.id.tv_greeting);
        tvPassword = findViewById(R.id.tv_password);

        // Get Data From Intent
//        String username = getIntent().getStringExtra("username");
//        String password = getIntent().getStringExtra("password");
        User user = (User) getIntent().getSerializableExtra("user");

        // Set Data to XML
        tvGreeting.setText("Hello, " + user.getUsername());
        tvPassword.setText("Password : " + user.getPassword());
    }
}
