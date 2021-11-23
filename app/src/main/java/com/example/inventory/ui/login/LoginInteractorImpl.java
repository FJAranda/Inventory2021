package com.example.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.LoginRepositoryImpl;
import com.example.inventory.utils.CommonUtils;

public class LoginInteractorImpl implements LoginContract.OnLoginListener{
    private static final long WAIT_TIME = 2000;
    private LoginContract.LoginInteractor presenter;
    private LoginContract.LoginRepository repository;

    public LoginInteractorImpl(LoginContract.LoginInteractor presenter) {
        this.presenter = presenter;
        this.repository = LoginRepositoryImpl.newInstance(this);
    }

    public void validateCredentials(User user){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(user.getEmail())){
                    presenter.onEmailEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getPassword())){
                    presenter.onPasswordEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(user.getPassword())){
                    presenter.onPasswordError();
                    return;
                }
                repository.login(user);
            }
        }, WAIT_TIME);
    }

    //Respuestas de LoginRepository
    @Override
    public void onSuccess(String message) {
        presenter.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        presenter.onFailure(message);
    }
}
