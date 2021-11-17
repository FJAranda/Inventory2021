package com.example.inventory.ui.login;

import android.view.View;

import com.example.inventory.data.model.User;

public class LoginPresenter implements LoginContract.Presenter, LoginInteractorImpl.LoginInteractor {
    private LoginContract.View view;
    private LoginInteractorImpl interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

    //region Metodos del contrato con la vista
    @Override
    public void validateCredentials(User user) {
        interactor.validateCredentials(user);
        view.showProgressBar();
    }
    //endregion

    //region Metodos del contrato con el interactor
    @Override
    public void onUserEmptyError() {
        view.hideProgressBar();
        view.setUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgressBar();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void onAuthenticationError() {

    }

    @Override
    public void onSuccess() {
        view.hideProgressBar();
        view.onSuccess();
    }
    //endregion
}
