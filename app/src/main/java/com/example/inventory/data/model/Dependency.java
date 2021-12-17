package com.example.inventory.data.model;

import java.io.Serializable;
import java.util.Objects;

public class Dependency implements Comparable, Serializable {
    public static final String TAG = "dependency";

    private String nombre;
    private String nombreCorto;
    private String descripcion;
    private String imagen;

    public Dependency(){}

    public Dependency(String nombre, String nombreCorto, String descripcion, String imagen) {
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.imagen = imagen;
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
        return ((Dependency)o).getNombre().equals(getNombre());
    }

    @Override
    public int compareTo(Object o) {
        if (((Dependency)o).getNombre().equals(getNombre())){
            return ((Dependency)o).getDescripcion().compareTo(getDescripcion());
        }else{
            return ((Dependency)o).getNombre().compareTo(getNombre());
        }
    }
}
