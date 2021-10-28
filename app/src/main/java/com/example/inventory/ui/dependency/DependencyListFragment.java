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
import com.example.inventory.databinding.FragmentDependencyListBinding;

public class DependencyListFragment extends Fragment {
    FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se indica a la activity que se va a modificar el menu
        setHasOptionsMenu(true);
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

    /**
     * Método que inicializa el componente recyclerview
     */
    private void initRvDependency() {
        //1- Inicializar adapter
        adapter = new DependencyAdapter();
        //2- Indicar el diseño del recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //3- Asignar Layout al recyclerview
        binding.rvDependency.setLayoutManager(linearLayoutManager);
        //4- Asignar adapter al recyclerview
        binding.rvDependency.setAdapter(adapter);
    }
}