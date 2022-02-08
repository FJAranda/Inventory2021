package com.example.inventory.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Section implements Comparable, Serializable {
    public static final String TAG = "section";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nombre;
    @NonNull
    private String nombreCorto;
    @NonNull
    private String dependencia;
    @NonNull
    private String descripcion;
    private String imagen;

    @Ignore
    public Section(String nombre, String nombreCorto, String dependencia, String descripcion, String imagen) {
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.dependencia = dependencia;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Section(int id, String nombre, String nombreCorto, String dependencia, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.dependencia = dependencia;
        this.descripcion = descripcion;
    }

    @Ignore
    public Section() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        return ((Section)o).getNombreCorto().equals(getNombreCorto());
    }

    @Override
    public int compareTo(Object o) {
        if (((Section)o).getNombreCorto().equals(getNombreCorto())){
            return ((Section)o).getNombre().compareTo(getNombre());
        }else{
            return ((Section)o).getDescripcion().compareTo(getDescripcion());
        }
    }
}
