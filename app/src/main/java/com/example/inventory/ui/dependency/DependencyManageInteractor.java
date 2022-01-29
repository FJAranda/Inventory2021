package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.data.repository.DependencyRepositoryRoom;

public class DependencyManageInteractor implements  DependencyManageContract.OnManageCallback{
    private DependencyManageContract.ListenerInteractor listener;

    public DependencyManageInteractor(DependencyManageContract.ListenerInteractor listener) {
        this.listener = listener;
    }

    //Respuestas del interactor para el presenter
    @Override
    public void onAddSuccess(String message) {
        listener.onAddSuccess(message);
    }

    @Override
    public void onAddFailure(String message) {

    }

    @Override
    public void onEditSuccess(String message) {
        listener.onEditSuccess(message);
    }

    @Override
    public void onEditFailure(String message) {

    }

    //Metodos que el interactor envia hacer al repositorio
    public void add(Dependency dependency){
        DependencyRepositoryRoom.getInstance().add(dependency, this);
    }

    public void edit(String nombreCorto, Dependency dependency){
        DependencyRepositoryRoom.getInstance().edit(nombreCorto, dependency, this);
    }

}
