package com.example.inventory.ui.login;

import com.example.inventory.base.IBasePresenter;
import com.example.inventory.base.IProgressView;
import com.example.inventory.base.OnRepositoryCallBack;
import com.example.inventory.data.model.User;

public interface LoginContract {
    interface View extends OnRepositoryCallBack, IProgressView {
        //Modifica elementos de la vista
        void setUserEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();
        void setUserError();
    }
    interface Presenter extends IBasePresenter {
        void validateCredentials(User user);
    }

    interface LoginRepository{
        void login(User user);

    }

    //Interfaz que tienen que implementar los presentadores que quieran usar la clase LoginInteractor
    interface LoginInteractor extends OnRepositoryCallBack{
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
        void onEmailError();
    }
}
