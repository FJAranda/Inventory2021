package com.example.inventory.ui.product;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentProductDescriptionBinding;
import com.example.inventory.databinding.FragmentProductInfoBinding;

public class ProductInfoFragment extends Fragment {
    FragmentProductInfoBinding binding;
    public static final String TAG = "productInfoFragment";

    public static Fragment newInstance(Bundle bundle){
        ProductInfoFragment fragment = new ProductInfoFragment();
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
        binding = FragmentProductInfoBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}