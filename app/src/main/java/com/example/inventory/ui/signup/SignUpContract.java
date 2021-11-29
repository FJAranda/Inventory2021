package com.example.inventory.ui.signup;

import com.example.inventory.base.IBasePresenter;
import com.example.inventory.base.IProgressView;
import com.example.inventory.base.OnRepositoryCallBack;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;

public interface SignUpContract {

    interface SignUpRepository{
        void signUp(User user);
    }

    interface SignUpInteractor extends LoginContract.LoginInteractor {
        void onConfirmPasswordError();
    }

    interface SignUpPresenter extends IBasePresenter {
        void validateCredentials(User user, String password);
    }

    interface View extends OnRepositoryCallBack, IProgressView {
        void setEmailEmptyError();
        void setEmailError();
        void setPasswordEmptyError();
        void setConfirmPasswordError();
        void setPasswordError();
    }

}
