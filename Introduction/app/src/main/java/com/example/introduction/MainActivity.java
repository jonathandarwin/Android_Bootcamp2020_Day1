package com.example.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.introduction.model.User;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;

    // alt + ins
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Function yang pertama kali dijalankan
        // ketika MainActivity dibuka
        setContentView(R.layout.main_activity);

        // assign object by using findViewById()
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        // set an event listener
        // then the button is clicked, then run the method inside this listener (onClick method)
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get what user inputted using etUsername.getText()
                // convert it to string(toString())
                // trim it (.trim() -> delete all spaces in the front and back of the string)
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // validation
                if(username.isEmpty() || password.isEmpty()){
                    // display alert using Toast
                    // 3 param : context, text, duration
                    // dont forget to call .show() to show the Toast!
                    Toast.makeText(MainActivity.this,
                            "Please input your username and password",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    // Pindah halaman
                    // 2 parameter : source, destination
                    Intent intent = new Intent(MainActivity.this,
                            HomeActivity.class);
                    // 2 Parameter : nama, value
//                    intent.putExtra("username", username);
//                    intent.putExtra("password", password);

                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);

                    // using serializable
                    // therefore, User model should be impelements serializable
                    // if you want to pass your data faster, you can use parcelable instead.
                    intent.putExtra("user", user);

                    // start a new activity
                    startActivity(intent);

                    // you can call finish() if you don't want to go back to this activity
//                    finish();
                }

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("<LIFECYCLE>", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("<LIFECYCLE>", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("<LIFECYCLE>", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("<LIFECYCLE>", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("<LIFECYCLE>", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("<LIFECYCLE>", "onDestroy");
    }
}
