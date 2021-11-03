package com.example.inventory.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment implements View.OnClickListener{

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        binding.imgBtnAbout.setOnClickListener(this);
        binding.imgBtnDependency.setOnClickListener(this);
        binding.imgBtnInventory.setOnClickListener(this);
        binding.imgBtnProduct.setOnClickListener(this);
        binding.imgBtnSector.setOnClickListener(this);
        binding.imgBtnSettings.setOnClickListener(this);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Método que maneja los eventos onClick de la interfaz
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBtnAbout:
                showAboutFragment();
                break;
            case R.id.imgBtnInventory:
                showAddInventoryFragment();
                break;
            case R.id.imgBtnDependency:
                showDependencyListFragment();
                break;
        }
    }

    private void showDependencyListFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_DashboardFragment_to_dependencyListFragment);
    }

    private void showAddInventoryFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_DashboardFragment_to_addInventoryFragment);
    }

    //Inicializa y muestra el fragment About
    private void showAboutFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_DashboardFragment_to_aboutFragment);
    }
}