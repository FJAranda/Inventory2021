package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyListPresenter implements DependencyListContract.Presenter, DependencyListContract.OnInteractorListener {

    private DependencyListContract.view view;
    private DependencyListInteractor interactor;

    public DependencyListPresenter(DependencyListContract.view view) {
        this.view = view;
        this.interactor = new DependencyListInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;

    }

    @Override
    public void load() {
        view.showProgress();
        interactor.load();
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void undo(Dependency dependency) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <t> void onSucces(List<t> list) {
        if (list.size() == 0){
            view.showNoData();
        }else{
            view.showData((ArrayList<Dependency>)list);
        }
        view.hideProgress();
    }

    @Override
    public void onDeleteSuccess(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }
}
