package com.example.inventory.ui.login;

import android.view.View;

import com.example.inventory.data.model.User;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.LoginInteractor {
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
    public void onEmailEmptyError() {
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
        view.hideProgressBar();
        view.setPasswordError();
    }

    @Override
    public void onEmailError() {
        view.hideProgressBar();
        view.setUserError();
    }

    @Override
    public void onSuccess(String message) {
        view.hideProgressBar();
        view.onSuccess("");
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.onFailure(message);
    }

    //endregion
}
