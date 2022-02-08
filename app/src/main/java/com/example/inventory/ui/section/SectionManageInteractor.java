package com.example.inventory.ui.section;

import com.example.inventory.data.model.Section;
import com.example.inventory.data.repository.SectionRepository;

public class SectionManageInteractor implements SectionManageContract.OnManageCallback{
    SectionManageContract.Listener listener;

    public SectionManageInteractor(SectionManageContract.Listener listener) {
        this.listener = listener;
    }

    public void add(Section section){
        SectionRepository.getInstance().add(section, this);
    }

    public void edit(String nombreCorto, Section section){
        SectionRepository.getInstance().edit(nombreCorto, section, this);
    }

    @Override
    public void onAddSuccess(Section section) {
        listener.onAddSuccess(section);
    }

    @Override
    public void onAddFailure(Section section) {
        listener.onAddFailure(section);
    }

    @Override
    public void onEditSuccess(Section section) {
        listener.onEditSuccess(section);
    }

    @Override
    public void onEditFailure(Section section) {
        listener.onEditFailure(section);
    }
}
