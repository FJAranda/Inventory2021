package com.example.inventory.ui.dependency;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.utils.StateManage;

public class DependencyManageVM extends ViewModel{
    MutableLiveData<Dependency> dependency;
    MutableLiveData<Boolean> isAdd;
    StateManage stateManage;
    //MutableLiveData<Boolean> addSuccess;
    //MutableLiveData<Boolean> editSuccess;

    public MutableLiveData<Dependency> getDependency() {
        return dependency;
    }

    public DependencyManageVM() {
        this.dependency = new MutableLiveData<>();
    }

    public DependencyManageVM(Dependency dependency){
        if (dependency == null){
            stateManage.setState(StateManage.State.ADD);
            //isAdd = new MutableLiveData<>(true);
            this.dependency = new MutableLiveData<>(new Dependency("","","",""));
        }else{
            stateManage.setState(StateManage.State.EDIT);
            //isAdd = new MutableLiveData<>(false);
            this.dependency = new MutableLiveData<>(dependency);
        }
        //addSuccess = new MutableLiveData<>();
        //editSuccess = new MutableLiveData<>();
        stateManage = new StateManage();

    }

    public void manage(){
        //if (isAdd.getValue()){
        if (stateManage.getState() == StateManage.State.ADD){
            if(DependencyRepository.getInstance().add(this.dependency.getValue())){
                stateManage.setState(StateManage.State.SUCCESSADD);
            }else{
                stateManage.setState(StateManage.State.FAILURE);
            }
        }else{
            if(DependencyRepository.getInstance().edit(this.dependency.getValue())){
                stateManage.setState(StateManage.State.SUCESSEDIT);
            }else{
                stateManage.setState(StateManage.State.FAILURE);
            }
        }
    }
}
