package com.example.inventory.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.inventory.data.dao.DependencyDAO;
import com.example.inventory.data.dao.SectionDAO;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Defino la clase de la base de datos
@Database(entities = {Dependency.class, Section.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {

    //Creo los metodos de obtencion del dao
    public abstract DependencyDAO dependencyDAO();
    public abstract SectionDAO sectionDAO();
    private static volatile MyDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "inventory")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

  
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "inventory")
                            .build();
                }
            }
        }
    }

    public static MyDatabase getDatabase() {
        return INSTANCE;

    }
}
