package com.example.inventory.ui.section;

import com.example.inventory.data.model.Section;
import com.example.inventory.data.repository.SectionRepository;

import java.util.List;

public class SectionListInteractor implements SectionListContract.OnListCallback{
    SectionListContract.Listener listener;

    public SectionListInteractor(SectionListContract.Listener listener) {
        this.listener = listener;
    }

    public void getList(){
        SectionRepository.getInstance().getList(this);
    }

    public void delete(Section section){
        SectionRepository.getInstance().delete(section, this);
    }

    public void undo(Section section){
        SectionRepository.getInstance().undo(section, this);
    }

    @Override
    public void onSuccess(List<Section> list) {
        listener.onSuccess(list);
    }

    @Override
    public void onFailure() {
        listener.onFailure();
    }

    @Override
    public void onDeleteSuccess(Section section) {
        listener.onDeleteSuccess(section);
    }

    @Override
    public void onDeleteFailure(Section section) {
        listener.onDeleteFailure(section);
    }

    @Override
    public void onUndoSuccess(Section section) {
        listener.onUndoSuccess(section);
    }

    @Override
    public void onUndoFailure(Section section) {
        listener.onUndoFailure(section);
    }
}
