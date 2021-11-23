package com.example.inventory.data.repository;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;

import java.util.ArrayList;

public class LoginRepositoryStatic implements LoginContract.LoginRepository {
    private static LoginRepositoryStatic instance;
    private LoginContract.OnLoginListener listener;
    private ArrayList<User> users;

    private  LoginRepositoryStatic(){

        users=new ArrayList<>();
        initialice();

    }

    private void initialice(){
        users.add(new User("a@a.com","Javi-1234."));

    }

    private static LoginRepositoryStatic getInstance(LoginContract.OnLoginListener listener){

        if (instance==null)
            instance=new LoginRepositoryStatic();
        instance.listener=listener;
        return instance;
    }


    @Override
    public void login(User user) {
        for (User item:users){

            if (item.getEmail().equals(user.getEmail())&&item.getPassword().equals(user.getPassword())){
                listener.onSuccess("Usuario Correcto");
                return;

            }

        }

//En caso de que no exista

        listener.onFailure("Error en la autenticación");
    }
}
