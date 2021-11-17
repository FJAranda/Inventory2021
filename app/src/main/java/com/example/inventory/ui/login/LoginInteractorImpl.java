package com.example.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory.data.model.User;

public class LoginInteractorImpl {
    private static final long WAIT_TIME = 2000;
    private LoginInteractor presenter;

    //Interfaz que tienen que implementar los presentadores que quieran usar la clase LoginInteractor
    interface LoginInteractor{
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
        void onAuthenticationError();
        void onSuccess();
    }

    public LoginInteractorImpl(LoginInteractor presenter) {
        this.presenter = presenter;
    }

    public void validateCredentials(User user){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(user.getUser())){
                    presenter.onUserEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getPassword())){
                    presenter.onPasswordEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getUser())){
                    presenter.onUserEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getUser())){
                    presenter.onUserEmptyError();
                    return;
                }
                presenter.onSuccess();
            }
        }, WAIT_TIME);
    }
}
