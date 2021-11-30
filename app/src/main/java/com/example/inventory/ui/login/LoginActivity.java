package com.example.inventory.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inventory.MainActivity;
import com.example.inventory.R;
import com.example.inventory.base.Event;
import com.example.inventory.data.model.User;
import com.example.inventory.databinding.ActivityLoginBinding;
import com.example.inventory.ui.signup.SignUpActivity;
import com.example.inventory.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

        binding.tiledtPassword.addTextChangedListener(new LoginTextWatcher(binding.tiledtPassword));
        binding.tiledtEmail.addTextChangedListener(new LoginTextWatcher(binding.tiledtEmail));
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

        EventBus.getDefault().register(this);
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.onDestroy();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter = null;
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
    public void setUserError() {
        binding.tilEmail.setError(getString(R.string.emailError));
    }

    @Override
    public void showProgress() {
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String message) {
        startMainActivity();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }
    //endregion

    //#region textwatcher
        class LoginTextWatcher implements TextWatcher{
        private View view;

        LoginTextWatcher (View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()){
                case R.id.tiledtEmail:
                    validateEmail(s.toString());
                break;
                case R.id.tiledtPassword:
                    validatePassword(s.toString());
                break;
            }
        }
    }

    private void validatePassword(String str) {
        if (TextUtils.isEmpty(str)){
            binding.tilPassword.setError(getString(R.string.passwordEmptyError));
        }else if (!CommonUtils.isPasswordValid(str)){
            binding.tilPassword.setError(getString(R.string.passwordError));
        }else{
            binding.tilPassword.setError(null);
        }
    }

    private void validateEmail(String str) {
        if (TextUtils.isEmpty(str)){
            binding.tilEmail.setError(getString(R.string.emailEmptyError));
        }else if(!Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
            binding.tilEmail.setError(getString(R.string.passwordError));
        }else{
            binding.tilEmail.setError(null);
        }

    }
    //endregion

    @Subscribe
    public void onEvent(Event event) {
        hideProgress();
        Toast.makeText(this, event.getMessage(),Toast.LENGTH_SHORT).show();
    }
}