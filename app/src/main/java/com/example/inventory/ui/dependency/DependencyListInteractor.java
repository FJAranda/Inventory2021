package com.example.inventory.ui.dependency;

import com.example.inventory.base.OnRepositoryListCallback;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;

import java.util.ArrayList;
import java.util.List;

public class DependencyListInteractor implements OnRepositoryListCallback {
    DependencyListContract.OnInteractorListener listener;

    public DependencyListInteractor(DependencyListContract.OnInteractorListener listener){
        this.listener = listener;
    }

    //Metodos de respuesta que van al presenter
    @Override
    public void onDeleteSuccess(String message) {
        listener.onDeleteSuccess(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSucces(List<T> list) {
        listener.onSucces(list);
    }

    //Metodos que el interactor envia al repositorio
    public void load() {
        DependencyRepository.getInstance().getList(this);
    }

    public void delete(Dependency dependency){
        DependencyRepository.getInstance().delete(dependency, this);
    }

    public void undo(Dependency dependency){
        DependencyRepository.getInstance().undo(dependency, this);
    }

}
