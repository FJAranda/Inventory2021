package com.example.inventory.ui.login;

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
        view.showProgress();
    }
    //endregion

    //region Metodos del contrato con el interactor
    @Override
    public void onEmailEmptyError() {
        view.hideProgress();
        view.setUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgress();
        view.setPasswordError();
    }

    @Override
    public void onEmailError() {
        view.hideProgress();
        view.setUserError();
    }

    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.onSuccess("");
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    @Override
    public void onDestroy() {
        this.view=null;
        this.interactor = null;
    }

    //endregion
}
