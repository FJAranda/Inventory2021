package com.example.inventory.ui.login;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.LoginRepositoryImpl;
import com.example.inventory.utils.CommonUtils;
import com.example.inventory.utils.StateView;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<User> user;
    private StateView state;
    private MutableLiveData<Integer> error;
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public MutableLiveData<Integer> getError() {
        return error;
    }

    public StateView getState() {
        return state;
    }

    public LoginViewModel() {
        state = new StateView();
        user = new MutableLiveData<>();
        error = new MutableLiveData<>();
        email = new MutableLiveData<>();
        password = new MutableLiveData<>();
    }

    public void validateCredentials(){
        //Estado de la vista pasa a loading
        state.setValue(StateView.State.LOADING);
        //Casos de uso
        if (TextUtils.isEmpty(email.getValue())) {
            error.postValue(R.string.emailEmptyError);
            state.setState(StateView.State.ERROR);
            return;
        }
        if (TextUtils.isEmpty(password.getValue())) {
            error.postValue(R.string.passwordEmptyError);
            state.setState(StateView.State.ERROR);
            return;
        }
        if (!CommonUtils.isPasswordValid(password.getValue())) {
            error.postValue(R.string.passwordError);
            state.setState(StateView.State.ERROR);
            return;
        }
        Log.d("ONCLICK", "Login");
        //TODO: llamar al repositorio y modificar user
        user.postValue(LoginRepositoryImpl.newInstance().loginRoom(email.getValue(), password.getValue()));
        if (user.getValue() != null){
            state.setState(StateView.State.COMPLETED);
        }else{
            state.setState(StateView.State.ERROR);
        }
    }

}
