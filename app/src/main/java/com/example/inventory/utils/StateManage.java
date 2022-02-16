package com.example.inventory.utils;

import androidx.lifecycle.MutableLiveData;

public class StateManage extends MutableLiveData {
    private StateManage.State state;

    public StateManage.State getState() {
        return state;
    }

    public void setState(StateManage.State state) {
        setValue(this.state = state);
    }

    public enum State{
        ADD,
        EDIT,
        SUCCESSADD,
        SUCESSEDIT,
        FAILURE
    }
}
