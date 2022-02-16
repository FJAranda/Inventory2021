package com.example.inventory.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.preference.PreferenceManager;
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
import com.example.inventory.utils.StateView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginActivity extends AppCompatActivity {

    private LoginContract.Presenter presenter;
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    private Button btnRegistrar;
    private Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewmodel(viewModel);

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

        /* Se hace mediante databinding
        btnLogin.setOnClickListener( v -> {
            viewModel.validateCredentials();
        });*/

        //Se vincula el ciclo de vida con el observer, dentro del livedata
        viewModel.getError().observe(this, error ->{
            switch (error){
                case R.string.emailEmptyError:
                    setUserEmptyError();
                    break;
                case R.string.passwordEmptyError:
                    setPasswordEmptyError();
                    break;
                case R.string.passwordError:
                    setPasswordError();
                    break;
            }
        });

        viewModel.getState().observe(this, object ->{
            StateView.State state = (StateView.State) object;
            switch (state){
                case LOADING:
                    showProgress();
                    break;
                case COMPLETED:
                    hideProgress();
                    startMainActivity();
                    break;
                case ERROR:
                    hideProgress();
                    break;
            }
        });

    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


    //region Metodos del contrato con el presenter
    public void setUserEmptyError() {
        binding.tilEmail.setError(getString(R.string.emailEmptyError));
    }

    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.passwordEmptyError));
    }

    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.passwordError));
    }

    public void setUserError() {
        binding.tilEmail.setError(getString(R.string.emailError));
    }

    public void showProgress() {
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        binding.pbLogin.setVisibility(View.GONE);
    }

    public void onSuccess(String message) {
        if (binding.cbRemenber.isChecked()){
        SharedPreferences.Editor sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferences.putString(User.TAG, binding.tiledtEmail.getText().toString());
        sharedPreferences.apply();
        }
        startMainActivity();
    }

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
}