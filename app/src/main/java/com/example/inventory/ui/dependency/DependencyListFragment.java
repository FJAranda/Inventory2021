package com.example.inventory.ui.dependency;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.base.BaseDialogFragment;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyListBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DependencyListFragment extends Fragment implements DependencyListContract.view, DependencyAdapter.OnManageDependencyListener {
    FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
    private DependencyListContract.Presenter presenter;
    private Dependency deletedDependency;
    private DependencyVM viewModel;

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
                presenter.order();
                return true;
            case R.id.action_order_descripcion:
                adapter.orderByDescripcion();
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
        getActivity().setTitle(R.string.app_name);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DependencyVM.class);
        viewModel.getDependencies().observe(getViewLifecycleOwner(), dependencies -> {
            adapter.update(dependencies);
            binding.swipeContainer.setRefreshing(false);
        });
        binding.setViewmodel(viewModel);
        initRvDependency();
        initFab();
    }

    private void initFab() {
        binding.floatingActionButton.setOnClickListener(v -> {
            DependencyListFragmentDirections.ActionDependencyListFragmentToDependencyManageFragment action =
                    DependencyListFragmentDirections.actionDependencyListFragmentToDependencyManageFragment(null);
            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //solicito los datos
        //presenter.load();
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
        DependencyListFragmentDirections.ActionDependencyListFragmentToDependencyManageFragment action =
                DependencyListFragmentDirections.actionDependencyListFragmentToDependencyManageFragment(dependency);
        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {
        Log.d("VIEW", "ONDELETEDEPENDENCY");
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, "Elimminar Elemento");
        bundle.putString(BaseDialogFragment.MESSAGE, "¿Desea eliminar el elemento: " + dependency.getNombre() + "?");
        //Conectar el dialogFragment en el grefico de navegacion para poder navegar
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("VIEW", "ONFRAGMENTRESULT");
                //Si la respuesta es true, se realiza lo que programemos aqui
                if (result.getBoolean(BaseDialogFragment.KEY_BUNDLE)){
                    deletedDependency = dependency;
                    presenter.delete(dependency);
                }
            }
        });
        NavHostFragment.findNavController(this).navigate(R.id.action_dependencyListFragment_to_baseDialogFragment, bundle);
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
        adapter.delete(deletedDependency);
        Snackbar.make(getView(), message, BaseTransientBottomBar.LENGTH_SHORT)
                .setAction(getString(R.string.strUndo), v ->{
                    presenter.undo(deletedDependency);
                }).show();
        if (adapter.getItemCount() == 0){
            showNoData();
        }
    }

    @Override
    public void onUndoSuccess(String message) {
        adapter.undo(deletedDependency);
        if (binding.llNoDataDependencyList.getVisibility() == View.VISIBLE){
            binding.llNoDataDependencyList.setVisibility(View.GONE);
        }
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
        if (binding.llNoDataDependencyList.getVisibility() == View.VISIBLE){
            binding.llNoDataDependencyList.setVisibility(View.GONE);
        }
        adapter.update(list);
    }

    @Override
    public void showNoData() {
        binding.llNoDataDependencyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDataOrder() {
        adapter.order();
    }

    @Override
    public void showDataInverseOrder() {
        adapter.inverseOrder();
    }
    //endregion
}