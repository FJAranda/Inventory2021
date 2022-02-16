package com.moronlu18.inventoryjetpack.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Esta clase será la utilizada para ver/comprobar el estado de una petición
 * a la base de datos, Api REST o Firebase.
 * @param <T>
 */
public class StateData<T> {
    //private Throwable;
    //Esta variable contendrá el id del string del error ocurrido (internacionalizado)
    @NonNull
    private int error;

    @NonNull
    private DataStatus status;

    @Nullable
    private T data;


    public StateData(){
        this.status= DataStatus.CREATED;
        this.data=null;
        this.error=-1;
    }
    public StateData<T> loading(){
        this.status= DataStatus.LOADING;
        this.data=null;
        this.error=-1;
        return this;
    }
    public StateData<T> success(T data){
        this.status= DataStatus.SUCCESS;
        this.data=data;
        this.error=-1;
        return this;
    }

    public StateData<T> error (int error){
        this.status= DataStatus.ERROR;
        this.data=null;
        this.error=error;
        return this;
    }
    public StateData<T> complete (){
        this.status= DataStatus.COMPLETE;
        return this;
    }

    @NonNull
    public DataStatus getStatus() {
        return this.status;
    }

    @NonNull
    public int getError() {
        return this.error;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public enum DataStatus {
        CREATED, LOADING, SUCCESS, ERROR, COMPLETE
    }
}
