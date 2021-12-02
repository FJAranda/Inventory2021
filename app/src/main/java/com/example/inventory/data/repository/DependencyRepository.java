package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.dependency.DependencyListContract;

import java.util.ArrayList;

public class DependencyRepository implements DependencyListContract.Repository {
    private DependencyListContract.OnDependencyCallback callback;
    private static DependencyRepository repository;
    private ArrayList<Dependency> list;

    private DependencyRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new Dependency("Aula 1", "A1", null, null));
        list.add(new Dependency("Aula 2", "A2", null, null));
        list.add(new Dependency("Aula 3", "A3", null, null));
        list.add(new Dependency("Aula 4", "A4", null, null));
        list.add(new Dependency("Aula 5", "A5", null, null));
    }

    public static DependencyRepository getInstance(DependencyListContract.OnDependencyCallback callback){
        if (repository == null){
            repository = new DependencyRepository();
        }
        repository.callback = callback;
        return repository;
    }

    @Override
    public void getList() {
        callback.onSucces(list);
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void undo(Dependency dependency) {

    }
}
