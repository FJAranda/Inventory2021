package com.example.inventory.ui.dependency;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Index;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.data.repository.DependencyRepositoryRoom;

import java.util.ArrayList;

public class DependencyVM extends ViewModel {
    private MutableLiveData<ArrayList<Dependency>> dependencies;
    private MutableLiveData<Integer> size;
    private MutableLiveData<Boolean> empty;
    private MutableLiveData<Dependency.Order> orden;

    public MutableLiveData<ArrayList<Dependency>> getDependencies() {
        return dependencies;
    }

    public MutableLiveData<Integer> getSize() {
        return size;
    }

    public MutableLiveData<Boolean> getEmpty() {
        return empty;
    }

    public MutableLiveData<Dependency.Order> getOrden() {
        return orden;
    }

    public DependencyVM() {
        dependencies = new MutableLiveData<>(DependencyRepositoryRoom.getInstance().getList());
        size = new MutableLiveData<>(dependencies.getValue().size());
        empty = new MutableLiveData<>(dependencies.getValue().isEmpty());
        orden = new MutableLiveData<>(Dependency.Order.SHORTNAME);
    }
}
