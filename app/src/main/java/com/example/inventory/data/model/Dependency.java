package com.example.inventory.data.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.squareup.picasso.Picasso;

@Entity
public class Dependency implements Comparable, Serializable {
    public static final String TAG = "dependency";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String nombreCorto;
    private String descripcion;
    private String imagen;

    @Ignore
    public Dependency(){}
    @Ignore
    public Dependency(String nombre, String nombreCorto, String descripcion, String imagen) {
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Dependency(int id, String nombre, String nombreCorto, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView imageView, String url, Drawable error){
        Picasso.get().load(url).error(error).into(imageView);
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

    @Override
    public String toString() {
        return nombreCorto;
    }

    //Enumerado para elegir la opcion para ordenar dependencias
    public enum Order{
        SHORTNAME,
        NAME,
        ID
    }
}
