package com.example.inventory.data.repository;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;

import java.util.ArrayList;

public class RepositoryStatic implements LoginContract.LoginRepository, SignUpContract.SignUpRepository {
    private static RepositoryStatic repository;
    private LoginContract.OnLoginListener listener;
    private ArrayList<User> users;

    private RepositoryStatic(){

        users=new ArrayList<>();
        initialice();

    }

    private void initialice(){
        users.add(new User("a@a.com","Javi-1234."));

    }

    private static RepositoryStatic getInstance(LoginContract.OnLoginListener listener){

        if (repository == null)
            repository = new RepositoryStatic();
        repository.listener = listener;
        return repository;
    }

    @Override
    public void login(User user) {
        for (User item:users){
            if (item.getEmail().equals(user.getEmail())&&item.getPassword().equals(user.getPassword())){
                listener.onSuccess("Usuario Correcto");
                return;
            }
        }
        listener.onFailure("Error en la autenticación");
    }

    @Override
    public void signUp(User user) {
        for (User item:users){
            if (user.getEmail().equals(item.getEmail())){
                listener.onFailure("El correo ya existe.");
                return;
            }else{
                users.add(user);
                listener.onSuccess("Usuario creado con exito.");
            }
        }
    }
}
