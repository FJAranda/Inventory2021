package com.example.inventory.ui.section;

import com.example.inventory.data.model.Section;

import java.util.List;

public class SectionListPresenter implements SectionListContract.Presenter, SectionListContract.Listener{
    private SectionListContract.View view;
    private SectionListInteractor interactor;

    public SectionListPresenter(SectionListContract.View view) {
        this.view = view;
        interactor = new SectionListInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccess(List<Section> list) {
        view.onSuccess(list);
        view.hideProgress();
    }

    @Override
    public void onFailure() {
        view.onFailure();
        view.hideProgress();
    }

    @Override
    public void onDeleteSuccess(Section section) {
        view.onDeleteSuccess(section);
        view.hideProgress();
    }

    @Override
    public void onDeleteFailure(Section section) {
        view.onDeleteFailure(section);
        view.hideProgress();
    }

    @Override
    public void onUndoSuccess(Section section) {
        view.onUndoSuccess(section);
        view.hideProgress();
    }

    @Override
    public void onUndoFailure(Section section) {
        view.onUndoFailure(section);
        view.hideProgress();
    }

    @Override
    public void getList() {
        view.showProgress();
        interactor.getList();
    }

    @Override
    public void delete(Section section) {
        view.showProgress();
        interactor.delete(section);
    }

    @Override
    public void undo(Section section) {
        view.showProgress();
        interactor.undo(section);
    }
}
