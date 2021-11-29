package com.example.inventory.ui.signup;

import com.example.inventory.data.model.User;

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
        view.showProgress();
    }

    //Comunicacion con el interactor
    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgress();
        view.setEmailEmptyError();
    }

    @Override
    public void onEmailError() {
        view.hideProgress();
        view.setEmailError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onConfirmPasswordError() {
        view.hideProgress();
        view.setConfirmPasswordError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgress();
        view.setPasswordError();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }
}
