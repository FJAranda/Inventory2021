package com.example.inventory.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

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
            presenter.validateCredentials(new User(binding.tilEmail.getEditText().getText().toString(), binding.tiledtPassword.getText().toString()));
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
        binding.tilEmail.setError(getString(R.string.emailEmptyError));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.passwordEmptyError));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.passwordError));
    }

    @Override
    public void showProgressBar() {
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String message) {
        startMainActivity();
    }

    @Override
    public void onFailure(String message) {

    }
    //endregion
}