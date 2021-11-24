package com.example.inventory.ui.signup;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;

public class SignUpPresenter implements SignUpContract.SignUpPresenter, SignUpContract.SignUpInteractor {
    private SignUpContract.View view;
    private SignUpInteractor interactor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.interactor = new SignUpInteractor(this);
    }

    //Comunicacion con la vista
    @Override
    public void validateCredentials(User user, String password) {
        interactor.validateCredentials(user, password);
        view.showProgressBar();
    }

    //Comunicacion con el interactor
    @Override
    public void onSuccess(String message) {
        view.hideProgressBar();
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.onFailure(message);
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgressBar();
        view.setEmailEmptyError();
    }

    @Override
    public void onEmailError() {
        view.hideProgressBar();
        view.setEmailError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgressBar();
        view.setPasswordEmptyError();
    }

    @Override
    public void onConfirmPasswordError() {
        view.hideProgressBar();
        view.setConfirmPasswordError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgressBar();
        view.setPasswordError();
    }
}
