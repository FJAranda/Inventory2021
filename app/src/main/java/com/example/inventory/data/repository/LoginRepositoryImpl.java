package com.example.inventory.data.repository;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.login.LoginInteractorImpl;


//Simulamos que la instancia de loginrepository es unica (Patron singleton)
public class LoginRepositoryImpl implements LoginContract.LoginRepository{
    private static LoginRepositoryImpl instance;
    private LoginContract.OnLoginListener listener;

    private LoginRepositoryImpl() {
    }

    public static LoginRepositoryImpl newInstance(LoginContract.OnLoginListener loginListener){
        if (instance == null){
            instance = new LoginRepositoryImpl();
        }
        instance.listener = loginListener;
        return instance;
    }

    @Override
    public void login(User user) {

    }
}
