package com.example.inventory.ui.login;

import com.example.inventory.data.model.User;

public interface LoginContract {
    interface View extends  OnLoginListener{
        //Modifica elementos de la vista
        void setUserEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();

        void showProgressBar();
        void hideProgressBar();
    }
    interface Presenter{
        void validateCredentials(User user);
    }

    interface OnLoginListener{
        void onSuccess(String message);
        void onFailure(String message);
    }

    interface LoginRepository{
        void login(User user);

    }

    //Interfaz que tienen que implementar los presentadores que quieran usar la clase LoginInteractor
    interface LoginInteractor extends OnLoginListener{
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
    }
}
