package com.example.inventory.ui.dependency;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyListBinding;

import java.util.ArrayList;
import java.util.List;

public class DependencyListFragment extends Fragment implements DependencyListContract.view, DependencyAdapter.OnManageDependencyListener {
    FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
    private DependencyListContract.Presenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se indica a la activity que se va a modificar el menu
        setHasOptionsMenu(true);
        //Inicializar presenter
        presenter = new DependencyListPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dependency_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_dependency:
                Toast.makeText(getActivity(), "Opcion ordenar dependencias", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDependencyListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependency();
    }

    @Override
    public void onStart() {
        super.onStart();
        //solicito los datos
        presenter.load();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    /**
     * Método que inicializa el componente recyclerview
     */
    private void initRvDependency() {
        //1- Inicializar adapter
        adapter = new DependencyAdapter(new ArrayList<>(), this);
        //2- Indicar el diseño del recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //3- Asignar Layout al recyclerview
        binding.rvDependency.setLayoutManager(linearLayoutManager);
        //4- Asignar adapter al recyclerview
        binding.rvDependency.setAdapter(adapter);
    }

    //region Metodos del adapter
    @Override
    public void onEditDependency(Dependency dependency) {

    }

    @Override
    public void onDeleteDependency(Dependency dependency) {

    }
    //endregion

    //region Metodos del repositorio
    @Override
    public void onFailure(String message) {

    }

    @Override
    public <t> void onSucces(List<t> list) {

    }

    @Override
    public void onDeleteSuccess(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }
    //endregion

    //region Metodos de progressbar

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
    //endregion

    //region Metodos del presenter
    @Override
    public void showData(List<Dependency> list) {
        adapter.update(list);
    }

    @Override
    public void showNoData() {

    }
    //endregion
}