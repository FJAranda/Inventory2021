package com.example.inventory.ui.signup;

import com.example.inventory.data.model.User;

public interface SignUpContract {

    interface SignUpRepository{
        void signUp(User user);
    }

    interface OnSignUpListener{
        void onSuccess(String message);
        void onFailure(String message);
    }

    interface SignUpInteractor extends OnSignUpListener{
        void onEmailEmptyError();
        void onEmailError();
        void onPasswordEmptyError();
        void onConfirmPasswordError();
        void onPasswordError();
    }

    interface SignUpPresenter{
        void validateCredentials(User user, String password);
    }

    interface View extends OnSignUpListener{
        void setEmailEmptyError();
        void setEmailError();
        void setPasswordEmptyError();
        void setConfirmPasswordError();
        void setPasswordError();

        void showProgressBar();
        void hideProgressBar();
    }

}
