package com.example.inventory.data.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.inventory.data.dao.DependencyDAO;
import com.example.inventory.data.dao.SectionDAO;
import com.example.inventory.data.dao.UserDao;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.data.model.User;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Defino la clase de la base de datos
@Database(entities = {Dependency.class, Section.class, User.class}, version = 3)
public abstract class MyDatabase extends RoomDatabase {

    //Creo los metodos de obtencion del dao
    public abstract DependencyDAO dependencyDAO();
    public abstract SectionDAO sectionDAO();
    public abstract UserDao userDao();
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

    private static void prepopulate(Context context) {
        Log.d("MYDATABASE", "PREPOPULATE");
        UserDao dao = INSTANCE.userDao();
        User user = new User("javierarandacaro@gmail.com", "Javi-123");
        getDatabase(context).runInTransaction(() ->{
            dao.insert(user);
        });
    }


    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "inventory")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    databaseWriteExecutor.execute(()-> prepopulate(context));
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
    }

    public static MyDatabase getDatabase() {
        return INSTANCE;

    }
}
