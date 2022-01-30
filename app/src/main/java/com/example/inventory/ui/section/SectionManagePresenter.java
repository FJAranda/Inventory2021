package com.example.inventory.ui.section;

import com.example.inventory.data.model.Section;

public class SectionManagePresenter implements SectionManageContract.Presenter, SectionManageContract.Listener{
    SectionManageContract.View view;
    SectionManageInteractor interactor;

    public SectionManagePresenter(SectionManageContract.View view) {
        this.view = view;
        this.interactor = new SectionManageInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onAddSuccess(Section section) {
        view.onAddSuccess(section);
    }

    @Override
    public void onAddFailure(Section section) {
        view.onAddFailure(section);
    }

    @Override
    public void onEditSuccess(Section section) {
        view.onEditSuccess(section);
    }

    @Override
    public void onEditFailure(Section section) {
        view.onEditFailure(section);
    }

    @Override
    public void add(Section section) {
        interactor.add(section);
    }

    @Override
    public void edit(String nombreCorto, Section section) {
        interactor.edit(nombreCorto, section);
    }
}
