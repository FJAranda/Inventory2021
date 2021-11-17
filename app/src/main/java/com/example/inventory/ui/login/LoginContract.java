package com.example.inventory.ui.login;

import com.example.inventory.data.model.User;

public interface LoginContract {
    interface View{
        //Modifica elementos de la vista
        void setUserEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();
        void setAuthenticationError();

        //Desencadena la transicion
        void onSuccess();

        void showProgressBar();
        void hideProgressBar();
    }
    interface Presenter{
        void validateCredentials(User user);
    }
}
