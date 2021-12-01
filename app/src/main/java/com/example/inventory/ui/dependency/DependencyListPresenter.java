package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;

import java.util.List;

public class DependencyListPresenter implements DependencyListContract.Presenter, DependencyListContract.OnInteractorListener {

    @Override
    public void onDestroy() {

    }

    @Override
    public void load() {

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

    }

    @Override
    public void onDeleteSuccess(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }
}
