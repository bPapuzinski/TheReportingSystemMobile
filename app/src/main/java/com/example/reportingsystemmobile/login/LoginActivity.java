package com.example.reportingsystemmobile.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import com.example.reportingsystemmobile.R;
import com.example.reportingsystemmobile.menu.MainActivity;

public class LoginActivity extends Fragment {

    private View view;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button register;
    private LoginService loginService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        setupAllElements();


        loginButton.setOnClickListener(v -> {
            LoginData loginData = new LoginData();
            loginData.setUsername(usernameEditText.getText().toString());
            loginData.setPassword(passwordEditText.getText().toString());

            loginService.login(loginData);
        });

        register.setOnClickListener(v -> {
            displayToast("do wywalenia");
        });

        return view;
    }

    public void setupAllElements() {
        loginService = new LoginService(this);
        usernameEditText = view.findViewById(R.id.username_edittext);
        passwordEditText = view.findViewById(R.id.password_edittext);
        loginButton = view.findViewById(R.id.login_button);
        register = view.findViewById(R.id.registerAcc_button);
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void changeMenuContext() {
        ((MainActivity) getActivity()).userLoggedMenu();
    }
}
