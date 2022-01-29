package com.example.inventory.ui.product;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentProductMapBinding;

public class ProductFragmentMap extends Fragment {
    public static final String TAG = "productFragmentMap";
    FragmentProductMapBinding binding;

    public static ProductFragmentMap newInstance(Bundle bundle) {
        ProductFragmentMap fragment = new ProductFragmentMap();
        if (bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductMapBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}