package com.moronlu18.inventoryjetpack.utils;

import androidx.lifecycle.MutableLiveData;

/**
 * Esta clase es la que contiene los métodos que pueden modificar
 * un objeto StateData. Además, será la clase observada por el
 * LifeCycleOwner.
 * @param <T>
 */
public class StateLiveData<T> extends MutableLiveData<StateData> {


    public void postLoading() {
        postValue(new StateData().loading());
    }

    public void postSuccess(T data) {
        postValue(new StateData().success(data));

    }
    public void postComplete() {
        postValue(new StateData().complete());
    }

    public void postError(int error) {
        postValue(new StateData().error(error));
    }
}
