package com.example.inventory.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inventory.R;
import com.example.inventory.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private static final long WAIT_TIME = 2000;

    //Tiempo de espera que simula la conexion con la base de datos o configuracion de la app
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLogin();
            }
        }, WAIT_TIME);

    }

    private void startLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        //LLamo de forma explicita al metodo finish de una activity para eliminarla de la pila de actividades para que el usuario no
        //pueda volver a esta activity.
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

}