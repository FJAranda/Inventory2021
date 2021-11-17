package com.example.inventory.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventory.MainActivity;
import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.databinding.ActivityLoginBinding;
import com.example.inventory.ui.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private ActivityLoginBinding binding;
    private Button btnRegistrar;
    private Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this);

        btnLogin = binding.btnSignIn;
        btnRegistrar = binding.btnSignUp;
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener( v -> {
            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //startActivity(intent);
            presenter.validateCredentials(new User(binding.tiledtUser.getText().toString(), binding.tiledtPassword.getText().toString()));
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter = null;
        }
    }

    //region Metodos del contrato con el presenter
    @Override
    public void setUserEmptyError() {
        binding.tilUser.setError(getString(R.string.userEmptyError));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.passwordEmptyError));
    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void setAuthenticationError() {

    }

    @Override
    public void onSuccess() {
        startMainActivity();
    }

    @Override
    public void showProgressBar() {
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.pbLogin.setVisibility(View.GONE);
    }
    //endregion
}