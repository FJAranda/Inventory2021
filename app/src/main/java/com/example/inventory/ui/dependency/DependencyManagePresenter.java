package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;

public class DependencyManagePresenter implements DependencyManageContract.Presenter, DependencyManageContract.ListenerInteractor {
    private DependencyManageContract.View view;
    private DependencyManageInteractor interactor;

    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
        this.interactor = new DependencyManageInteractor(this);
    }

    @Override
    public void onDestroy() {

    }

    //Metodos de respuesta para la vista
    @Override
    public void onAddSuccess(String message) {
        view.onAddSuccess(message);
    }

    @Override
    public void onAddFailure(String message) {

    }

    @Override
    public void onEditSuccess(String message) {
        view.onEditSuccess(message);
    }

    @Override
    public void onEditFailure(String message) {

    }

    //metodos que llaman al interactor

    @Override
    public void edit(String nombreCorto, Dependency dependency) {
        interactor.edit(nombreCorto, dependency);
    }

    @Override
    public void add(Dependency dependency) {
        interactor.add(dependency);
    }
}
