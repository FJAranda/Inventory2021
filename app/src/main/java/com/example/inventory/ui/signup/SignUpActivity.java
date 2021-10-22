package com.example.inventory.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.inventory.R;
import com.example.inventory.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnEnviarSignUp.setOnClickListener(view -> onBackPressed());

    }
}