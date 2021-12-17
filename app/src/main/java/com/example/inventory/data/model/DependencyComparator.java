package com.example.inventory.data.model;

import java.util.Comparator;

//Clase que se encarga de indicar cómo se ordena una dependencia por un atributo distinto del escogido en el método equals de Dependency
public class DependencyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Dependency)o1).getDescripcion().compareTo(((Dependency)o2).getDescripcion());
    }
}
