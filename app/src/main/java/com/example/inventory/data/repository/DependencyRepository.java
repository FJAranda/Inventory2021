package com.example.inventory.data.repository;

import com.example.inventory.base.OnRepositoryListCallback;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.dependency.DependencyListContract;
import com.example.inventory.ui.dependency.DependencyManageContract;

import java.util.ArrayList;
import java.util.Collections;

public class DependencyRepository implements DependencyListContract.Repository {

    private static DependencyRepository repository;
    private ArrayList<Dependency> list;

    private DependencyRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new Dependency("Aula 1", "A1", "b", null));
        list.add(new Dependency("Aula 2", "A2", "c", null));
        list.add(new Dependency("Aula 3", "A3", "a", null));
        list.add(new Dependency("Aula 4", "A4", "d", null));
        list.add(new Dependency("Aula 5", "A5", "e", null));
    }

    public static DependencyRepository getInstance(){
        if (repository == null){
            repository = new DependencyRepository();
        }
        return repository;
    }

    @Override
    public void getList(OnRepositoryListCallback callback) {
        Collections.sort(list);
        callback.onSucces(list);
    }

    @Override
    public void delete(Dependency dependency, OnRepositoryListCallback callback) {
        list.remove(dependency);
        callback.onDeleteSuccess("Dependencia eliminada: " + dependency.getNombre());
    }

    @Override
    public void undo(Dependency dependency, OnRepositoryListCallback callback) {
        list.add(dependency);
        callback.onUndoSuccess("Operacion cancelar");
    }

    @Override
    public void edit(String nombreCorto, Dependency dependency, DependencyManageContract.OnManageCallback callback) {
        for (Dependency d : list){
            if (d.getNombreCorto().equals(nombreCorto)){
                d.setNombre(dependency.getNombre());
                d.setDescripcion(dependency.getDescripcion());
                d.setImagen(dependency.getImagen());
            }
        }
        callback.onEditSuccess("Dependencia " + nombreCorto + " editada.");
    }

    @Override
    public void add(Dependency dependency, DependencyManageContract.OnManageCallback callback) {
        list.add(dependency);
        callback.onAddSuccess("Dependencia " + dependency.getNombreCorto() + " añadida.");
    }
}
