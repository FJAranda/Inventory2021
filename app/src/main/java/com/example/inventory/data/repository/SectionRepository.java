package com.example.inventory.data.repository;

import android.util.Log;

import com.example.inventory.data.dao.SectionDAO;
import com.example.inventory.data.database.MyDatabase;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.section.SectionListContract;
import com.example.inventory.ui.section.SectionManageContract;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SectionRepository implements SectionListContract.Repository {

    private static SectionRepository repository;
    private ArrayList<Section> sections;
    private SectionDAO dao;

    private SectionRepository() {
        sections = new ArrayList<>();
        dao = MyDatabase.getDatabase().sectionDAO();
    }

    public static SectionRepository getInstance(){
        if (repository == null){
            repository = new SectionRepository();
        }
        return repository;
    }

    @Override
    public void getList(SectionListContract.OnListCallback callback) {
        try {
            sections = (ArrayList<Section>) MyDatabase.databaseWriteExecutor.submit(() -> dao.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(sections);
    }

    @Override
    public void delete(Section section, SectionListContract.OnListCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> dao.delete(section));
        callback.onDeleteSuccess(section);
    }

    @Override
    public void undo(Section section, SectionListContract.OnListCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> dao.insert(section));
        callback.onUndoSuccess(section);
    }

    @Override
    public void add(Section section, SectionManageContract.OnManageCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> dao.insert(section));
        callback.onAddSuccess(section);
    }

    @Override
    public void edit(String nombreCorto, Section section, SectionManageContract.OnManageCallback callback) {
        Log.d("SECTION REPOSITORY", String.valueOf(section.getId()));
        MyDatabase.databaseWriteExecutor.submit(() -> dao.update(section));
        callback.onEditSuccess(section);
    }
}
