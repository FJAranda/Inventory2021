package com.example.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DependencyDAO {
    @Insert()
    long insert(Dependency dependency);

    @Update()
    void update(Dependency dependency);

    @Delete()
    void delete(Dependency dependency);

    @Query("DELETE FROM dependency")
    void deleteAll();

    @Query("SELECT * FROM dependency ORDER BY nombreCorto ASC")
    List<Dependency> selectAll();

    @Query("SELECT * FROM dependency WHERE nombreCorto=:nombreCorto")
    Dependency findByNombreCorto(String nombreCorto);
}
