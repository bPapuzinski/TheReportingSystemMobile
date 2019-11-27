package com.example.reportingsystemmobile.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reportingsystemmobile.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText mobileNumberEditText;
    private Button registerButton;
    private RegisterService registerService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.username_editText);
        passwordEditText = findViewById(R.id.password_editText);
        emailEditText = findViewById(R.id.email_editText);
        mobileNumberEditText = findViewById(R.id.mobileNumber_editText);
        registerButton = findViewById(R.id.register_button);
        registerService = new RegisterService(this);

        registerButton.setOnClickListener(v -> {
            RegisterData registerData = new RegisterData();
            registerData.setUsername(usernameEditText.getText().toString());
            registerData.setPassword(passwordEditText.getText().toString());
            registerData.setEmail(emailEditText.getText().toString());
            registerData.setMobileNumber(mobileNumberEditText.getText().toString());

            registerService.register(registerData);
        });
    }

    public void changeActivity(Intent intent) {
        startActivity(intent);
    }

    public void displayToast(String message) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }

}
