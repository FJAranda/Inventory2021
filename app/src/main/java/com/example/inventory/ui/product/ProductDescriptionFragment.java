package com.example.inventory.ui.product;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentProductDescriptionBinding;

public class ProductDescriptionFragment extends Fragment {
    public static final String TAG = "productFragmentDescription";
    FragmentProductDescriptionBinding binding;

    public static ProductDescriptionFragment newInstance(Bundle bundle) {
        ProductDescriptionFragment fragment = new ProductDescriptionFragment();
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
        // Inflate the layout for this fragment
        binding = FragmentProductDescriptionBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}