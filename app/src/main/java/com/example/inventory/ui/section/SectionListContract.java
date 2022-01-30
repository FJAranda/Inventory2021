package com.example.inventory.ui.section;

import com.example.inventory.base.IBasePresenter;
import com.example.inventory.base.IProgressView;
import com.example.inventory.data.model.Section;

import java.util.List;

public interface SectionListContract {

    interface OnListCallback{
        void onSuccess(List<Section> list);
        void onFailure();
        void onDeleteSuccess(Section section);
        void onDeleteFailure(Section section);
        void onUndoSuccess(Section section);
        void onUndoFailure(Section section);
    }

    interface View extends OnListCallback, IProgressView {}

    interface Presenter extends IBasePresenter {
        void getList();
        void delete(Section section);
        void undo(Section section);
    }

    interface Listener extends OnListCallback{}

    interface Repository extends SectionManageContract.Repository{
        void getList(OnListCallback callback);
        void delete(Section section, OnListCallback callback);
        void undo(Section section, OnListCallback callback);
    }
}
