package com.example.inventory.ui.dependency;

import com.example.inventory.base.IBasePresenter;
import com.example.inventory.data.model.Dependency;

public interface DependencyManageContract {
    interface OnManageCallback{
        void onAddSuccess(String message);
        void onAddFailure(String message);
        void onEditSuccess(String message);
        void onEditFailure(String message);
    }

    interface View extends OnManageCallback{}

    interface Presenter extends IBasePresenter{
        void edit(String nombreCorto, Dependency dependency);
        void add(Dependency dependency);
    }

    interface ListenerInteractor extends OnManageCallback{}

    interface Repository{
        void edit(String nombreCorto, Dependency dependency, OnManageCallback callback);
        void add(Dependency dependency, OnManageCallback callback);
    }
}
