package com.example.inventory.ui.dependency;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.inventory.data.model.Dependency;
import com.moronlu18.inventoryjetpack.data.model.Dependency;

public class DependencyManageViewModelFactory implements ViewModelProvider.Factory {

    private Dependency mParam;


    public DependencyManageViewModelFactory(Dependency param) {
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DependencyManageVM(mParam);
    }

}
