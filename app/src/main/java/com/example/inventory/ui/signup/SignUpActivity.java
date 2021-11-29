package com.example.inventory.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;

import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.databinding.ActivitySignUpBinding;
import com.example.inventory.ui.login.LoginActivity;
import com.example.inventory.utils.CommonUtils;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View{
    private static final long WAIT_TIME = 2000;
    private ActivitySignUpBinding binding;
    private SignUpContract.SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SignUpPresenter(this);
        binding.btnEnviarSignUp.setOnClickListener(view -> presenter.validateCredentials(new User(binding.tilEmail.getEditText().getText().toString(),
                binding.tilPassword.getEditText().getText().toString()), binding.tilConfirmPassword.getEditText().getText().toString()));

        binding.tilEmail.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilEmail.getEditText()));
        binding.tilPassword.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilPassword.getEditText()));
        binding.tilConfirmPassword.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilConfirmPassword.getEditText()));
        binding.tilUser.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilUser.getEditText()));

    }

    //Comunicacion con el presenter
    @Override
    public void onSuccess(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, WAIT_TIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    //
    @Override
    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.emailEmptyError));
    }

    @Override
    public void setEmailError() {
        binding.tilEmail.setError(getString(R.string.emailError));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.passwordEmptyError));
    }

    @Override
    public void setConfirmPasswordError() {
        binding.tilConfirmPassword.setError(getString(R.string.passwordMatchError));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.passwordError));
    }

    @Override
    public void showProgress() {
        binding.pbSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbSignUp.setVisibility(View.GONE);

    }

    class SignUpTextWatcher implements TextWatcher {
        private View view;

        public SignUpTextWatcher(View view) {
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
                    if (TextUtils.isEmpty(s.toString())){
                        binding.tilEmail.setError(getString(R.string.emailEmptyError));
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                        binding.tilEmail.setError(getString(R.string.emailError));
                    }else{
                        binding.tilEmail.setError(null);
                    }
                break;
                case R.id.tiledtUsuario:
                    if (TextUtils.isEmpty(s.toString())){
                        binding.tilUser.setError(getString(R.string.userEmptyError));
                    }else{
                        binding.tilUser.setError(null);
                    }
                    break;
                case R.id.tiledtPassword:
                    if (TextUtils.isEmpty(s.toString())){
                        binding.tilPassword.setError(getString(R.string.passwordEmptyError));
                    }else if(!CommonUtils.isPasswordValid(s.toString())){
                        binding.tilPassword.setError(getString(R.string.passwordError));
                    }else{
                        binding.tilPassword.setError(null);
                    }
                    break;
                case R.id.tiledtConfirmPassword:
                    if (TextUtils.isEmpty(s.toString())){
                        binding.tilConfirmPassword.setError(getString(R.string.passwordEmptyError));
                    }else{
                        binding.tilConfirmPassword.setError(null);
                    }
                    break;
            }
        }
    }
}