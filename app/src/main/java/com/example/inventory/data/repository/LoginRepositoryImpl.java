package com.example.inventory.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.example.inventory.ui.login.LoginInteractorImpl;


//Simulamos que la instancia de loginrepository es unica (Patron singleton)
public class LoginRepositoryImpl implements LoginContract.LoginRepository{
    private static LoginRepositoryImpl instance;
    private LoginContract.OnLoginListener listener;
    private static final String TAG= LoginRepositoryImpl.class.getName();

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
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            listener.onSuccess("usuario correcto");

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            listener.onFailure("Error Autenticacion:"+task.getException());
                        }
                    }
                });
    }
}
