package com.example.inventory.utils;

import androidx.lifecycle.MutableLiveData;

public class StateView extends MutableLiveData {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        setValue(this.state = state);
    }

    public enum State{
        LOADING,
        ERROR,
        COMPLETED
    }
}
