package com.example.inventory.ui.dependency;

import java.util.List;

public class DependencyListInteractor implements DependencyListContract.OnDependencyCallback{
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
    public <t> void onSucces(List<t> list) {

    }
}
