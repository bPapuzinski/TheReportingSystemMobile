package com.example.reportingsystemmobile.register;

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

public class RegisterActivity extends Fragment {

    private View view;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText mobileNumberEditText;
    private Button registerButton;
    private RegisterService registerService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        setupElements();

        registerButton.setOnClickListener(v -> {
            RegisterData registerData = new RegisterData();
            registerData.setUsername(usernameEditText.getText().toString());
            registerData.setPassword(passwordEditText.getText().toString());
            registerData.setEmail(emailEditText.getText().toString());
            registerData.setMobileNumber(mobileNumberEditText.getText().toString());

            registerService.register(registerData);
        });
        return view;
    }

    private void setupElements() {
        usernameEditText = view.findViewById(R.id.username_editText);
        passwordEditText = view.findViewById(R.id.password_editText);
        emailEditText = view.findViewById(R.id.email_editText);
        mobileNumberEditText = view.findViewById(R.id.mobileNumber_editText);
        registerButton = view.findViewById(R.id.register_button);
        registerService = new RegisterService(this);
    }

    public void changeFragmentToLogin() {
        ((MainActivity) getActivity()).selectDrawerItem((((MainActivity) getActivity()).getMenu().findItem(R.id.nav_login)));
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

}
