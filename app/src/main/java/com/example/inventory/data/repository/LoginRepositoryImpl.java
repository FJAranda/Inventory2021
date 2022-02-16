package com.example.inventory.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.base.Event;
import com.example.inventory.base.OnRepositoryCallBack;
import com.example.inventory.data.dao.UserDao;
import com.example.inventory.data.database.MyDatabase;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.example.inventory.ui.login.LoginInteractorImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


//Simulamos que la instancia de loginrepository es unica (Patron singleton)
public class LoginRepositoryImpl implements LoginContract.LoginRepository, SignUpContract.SignUpRepository {
    private static LoginRepositoryImpl instance;
    private OnRepositoryCallBack listener;
    private static final String TAG= LoginRepositoryImpl.class.getName();
    private UserDao dao;
    private ArrayList<User> users;

    private LoginRepositoryImpl() {
        this.users = new ArrayList<>();
        dao = MyDatabase.getDatabase().userDao();
    }

    public static LoginRepositoryImpl newInstance(OnRepositoryCallBack loginListener){
        if (instance == null){
            instance = new LoginRepositoryImpl();
        }
        instance.listener = loginListener;
        return instance;
    }

    public static LoginRepositoryImpl newInstance(){
        if (instance == null){
            instance = new LoginRepositoryImpl();
        }
        return instance;
    }

    private void primerInsert(){
        MyDatabase.databaseWriteExecutor.submit(() -> dao.insert(new User("javierarandacaro@gmail.com", "Javi-123")));
    }

    @Override
    public void login(User user) {
        //primerInsert();
        Log.d("LoginRepositoryImplement", "ROOM");
        User mUser = null;
        try {
            mUser = (User)MyDatabase.databaseWriteExecutor.submit(() -> dao.login(user.getEmail(), user.getPassword())).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mUser != null){
            listener.onSuccess("Login correcto");
        }else{
            listener.onFailure("Login incorrecto");
        }
    }

    public User loginRoom(String email, String password) {
        //primerInsert();
        Log.d(email, password);
        User mUser = null;
        try {
            mUser = (User)MyDatabase.databaseWriteExecutor.submit(() -> dao.login(email, password)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mUser;
    }

    /*@Override
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
                            //listener.onFailure("Error Autenticacion:"+task.getException());
                            Event event = new Event();
                            event.setEventType(Event.onLoginError);
                            event.setMessage("Error de autenticaión eventbus");
                            EventBus.getDefault().post(event);
                        }
                    }
                });
    }*/

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
