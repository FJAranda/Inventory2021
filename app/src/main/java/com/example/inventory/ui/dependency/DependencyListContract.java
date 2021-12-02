package com.example.inventory.ui.dependency;

import com.example.inventory.base.IBasePresenter;
import com.example.inventory.base.IProgressView;
import com.example.inventory.base.OnRepositoryCallBack;
import com.example.inventory.base.OnRepositoryDeleteCallback;
import com.example.inventory.base.OnRepositoryListCallback;
import com.example.inventory.base.OnRepositoryUndoCallback;
import com.example.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public interface DependencyListContract {
    interface view extends OnRepositoryListCallback, IProgressView {
        void showData(List<Dependency> list);
        void showNoData();
    }

    interface Presenter extends IBasePresenter{
        //Carga datos
        void load();
        //Pulsacion larga
        void delete(Dependency dependency);
        //Snackbar undo
        void undo(Dependency dependency);
    }

    /** Casos de uso:
     * Listar
     * Eliminar
     * Deshacer
     */
    interface OnInteractorListener extends OnRepositoryListCallback {

    }

    interface Repository{
        //Carga datos
        void getList();
        //Pulsacion larga
        void delete(Dependency dependency);
        //Snackbar undo
        void undo(Dependency dependency);
    }

    //En caso de no querer unificar los casos de uso podemos usar una interfaz que extiende de los callback que necesito
    interface OnDependencyCallback extends OnRepositoryUndoCallback, OnRepositoryDeleteCallback{
        void onFailure(String message);
        //El tipo debe definirse en la interfaz que lo necesite
        <t> void onSucces(List<t> list);
    }
}
