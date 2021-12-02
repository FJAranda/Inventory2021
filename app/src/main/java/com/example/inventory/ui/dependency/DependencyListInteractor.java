package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;

import java.util.ArrayList;
import java.util.List;

public class DependencyListInteractor implements DependencyListContract.OnDependencyCallback{
    DependencyListContract.OnInteractorListener listener;

    public DependencyListInteractor(DependencyListContract.OnInteractorListener listener){
        this.listener = listener;
    }

    @Override
    public void onDeleteSuccess(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSucces(List<T> list) {
        listener.onSucces(list);
    }

    public void load() {
        DependencyRepository.getInstance(this).getList();
    }
}
