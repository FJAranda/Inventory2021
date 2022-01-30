package com.example.inventory.data.repository;

import android.util.Log;

import com.example.inventory.base.OnRepositoryListCallback;
import com.example.inventory.data.dao.DependencyDAO;
import com.example.inventory.data.database.MyDatabase;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.dependency.DependencyListContract;
import com.example.inventory.ui.dependency.DependencyManageContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DependencyRepositoryRoom implements DependencyListContract.Repository {

    private static DependencyRepositoryRoom repository;
    private ArrayList<Dependency> list;
    private DependencyDAO dependencyDAO;

    private DependencyRepositoryRoom(){
        list = new ArrayList<>();
        dependencyDAO = MyDatabase.getDatabase().dependencyDAO();
    }

    private void initialice() {
        list.add(new Dependency("Aula 1", "A1", "b", null));
        list.add(new Dependency("Aula 2", "A2", "c", null));
        list.add(new Dependency("Aula 3", "A3", "a", null));
        list.add(new Dependency("Aula 4", "A4", "d", null));
        list.add(new Dependency("Aula 5", "A5", "e", null));
    }

    public static DependencyRepositoryRoom getInstance(){
        if (repository == null){
            repository = new DependencyRepositoryRoom();
        }
        return repository;
    }

    public List<Dependency> getList(){
        try {
            list = (ArrayList<Dependency>) MyDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Dependency getDependencyByNombreCorto(String nombreCorto){
        Dependency dependency = null;
        try {
            dependency = MyDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.findByNombreCorto(nombreCorto)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dependency;
    }

    @Override
    public void getList(OnRepositoryListCallback callback) {
        try {
            list = (ArrayList<Dependency>) MyDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSucces(list);
    }

    @Override
    public void delete(Dependency dependency, OnRepositoryListCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.delete(dependency));
        callback.onDeleteSuccess("Dependencia eliminada: " + dependency.getNombre());
    }

    @Override
    public void undo(Dependency dependency, OnRepositoryListCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.insert(dependency));
        callback.onUndoSuccess("Operacion cancelar");
    }

    @Override
    public void edit(String nombreCorto, Dependency dependency, DependencyManageContract.OnManageCallback callback) {
        Log.d("DEPENDENCY REPOSITORY", String.valueOf(dependency.getId()));
        MyDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.update(dependency));
        callback.onEditSuccess("Dependencia " + nombreCorto + " editada.");
    }

    @Override
    public void add(Dependency dependency, DependencyManageContract.OnManageCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.insert(dependency));
        callback.onAddSuccess("Dependencia " + dependency.getNombreCorto() + " añadida.");
    }
}
