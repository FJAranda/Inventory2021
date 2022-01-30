package com.example.inventory.ui.section;

import com.example.inventory.base.IBasePresenter;
import com.example.inventory.data.model.Section;

public interface SectionManageContract {
    interface OnManageCallback{
        void onAddSuccess(Section section);
        void onAddFailure(Section section);
        void onEditSuccess(Section section);
        void onEditFailure(Section section);
    }

    interface View extends OnManageCallback{}

    interface Presenter extends IBasePresenter {
        void add(Section section);
        void edit(String nombreCorto, Section section);
    }

    interface Listener extends OnManageCallback {}

    public interface Repository {
        void add(Section section, OnManageCallback callback);
        void edit(String nombreCorto, Section section, OnManageCallback callback);
    }
}
