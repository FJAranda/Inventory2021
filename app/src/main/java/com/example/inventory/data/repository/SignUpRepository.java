package com.example.inventory.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.base.OnRepositoryCallBack;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.signup.SignUpContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpRepository implements SignUpContract.SignUpRepository {
    private static SignUpRepository instance;
    private static OnRepositoryCallBack listener;
    private static final String TAG = SignUpRepository.class.getName();

    private SignUpRepository(){
    }

    public static SignUpRepository newInstance(OnRepositoryCallBack listener){
        if (instance == null){
            instance = new SignUpRepository();
        }
        instance.listener = listener;
        return instance;
    }

    @Override
    public void signUp(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            listener.onSuccess("Usuario Creado correctamente");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            listener.onFailure("Error creación de usuario: "+ task.getException());
                        }
                    }
                });
    }
}
