package com.example.inventory.data.repository;

import com.example.inventory.base.OnRepositoryCallBack;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;

import java.util.ArrayList;

public class RepositoryStatic implements LoginContract.LoginRepository, SignUpContract.SignUpRepository {
    private static RepositoryStatic repository;
    private static OnRepositoryCallBack callback;
    private ArrayList<User> users;

    private RepositoryStatic(){

        users=new ArrayList<>();
        initialice();

    }

    private void initialice(){
        users.add(new User("a@a.com","Javi-1234."));

    }

    private static RepositoryStatic getInstance(OnRepositoryCallBack listener){
        if (repository == null)
            repository = new RepositoryStatic();
        callback = listener;
        return repository;
    }

    @Override
    public void login(User user) {
        for (User item:users){
            if (item.getEmail().equals(user.getEmail())&&item.getPassword().equals(user.getPassword())){
                callback.onSuccess("Usuario Correcto");
                return;
            }
        }
        callback.onFailure("Error en la autenticación");
    }

    @Override
    public void signUp(User user) {
        for (User item:users){
            if (user.getEmail().equals(item.getEmail())){
                callback.onFailure("El correo ya existe.");
                return;
            }else{
                users.add(user);
                callback.onSuccess("Usuario creado con exito.");
            }
        }
    }
}
