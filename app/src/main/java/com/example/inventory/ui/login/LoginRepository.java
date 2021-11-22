package com.example.inventory.ui.login;

import androidx.annotation.NonNull;

import com.example.inventory.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepository implements LoginContract.LoginRepository {
    private FirebaseAuth auth;
    private LoginRepository loginRepository;

    private LoginRepository() {
        this.auth = FirebaseAuth.getInstance();
    }

    public LoginRepository newInstance(){
        if (loginRepository == null){
            loginRepository = new LoginRepository();
        }
        return loginRepository;
    }

    @Override
    public void login(User user) {
        /*auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser = auth.getCurrentUser();
            }
        })*/
    }
}
