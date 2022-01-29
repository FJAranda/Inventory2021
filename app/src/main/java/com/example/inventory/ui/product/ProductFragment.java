package com.example.inventory.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentProductBinding;

public class ProductFragment extends Fragment {
    FragmentProductBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment(ProductInfoFragment.newInstance(null));
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null){
            getChildFragmentManager().beginTransaction().replace(R.id.productContent, fragment).commit();
        }
    }

    private void initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener( v ->{
            Fragment fragment = null;
            switch (v.getItemId()){
                case R.id.action_product_info:
                    loadFragment(ProductInfoFragment.newInstance(null));
                    break;
                case R.id.action_product_map:
                    loadFragment(ProductFragmentMap.newInstance(null));
                    break;
                case R.id.action_product_description:
                    loadFragment(ProductDescriptionFragment.newInstance(null));
                    break;
            }
            return true;
        });
    }
}