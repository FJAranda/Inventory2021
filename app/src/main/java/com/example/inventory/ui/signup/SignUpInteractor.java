package com.example.inventory.ui.signup;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.SignUpRepository;
import com.example.inventory.utils.CommonUtils;

public class SignUpInteractor implements SignUpContract.OnSignUpListener {
    private SignUpContract.SignUpRepository repository;
    private SignUpContract.SignUpInteractor presenter;

    public SignUpInteractor (SignUpContract.SignUpInteractor presenter) {
        this.presenter = presenter;
        this.repository = SignUpRepository.newInstance(this);
    }

    //Reglas de negocio
    public void validateCredentials(User user, String password){
        if (TextUtils.isEmpty(user.getEmail())){
            presenter.onEmailEmptyError();
            return;
        }
        if (TextUtils.isEmpty(user.getPassword())){
            presenter.onPasswordEmptyError();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()){
            presenter.onEmailError();
            return;
        }
        if (!user.getPassword().equals(password)){
            presenter.onConfirmPasswordError();
            return;
        }
        if (!CommonUtils.isPasswordValid(user.getPassword())){
            presenter.onPasswordError();
            return;
        }
        repository.signUp(user);
    }

    //Respuestas del repositorio
    @Override
    public void onSuccess(String message) {
        presenter.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        presenter.onFailure(message);
    }
}
