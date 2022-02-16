package com.example.inventory.ui.dependency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.InventoryApplication;
import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyManageBinding;
import com.example.inventory.utils.StateManage;
import com.google.android.material.snackbar.Snackbar;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class DependencyManageFragment extends Fragment implements DependencyManageContract.View{
    FragmentDependencyManageBinding binding;
    DependencyManagePresenter presenter;
    DependencyManageVM viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DependencyManagePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dependency_manage, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Si recibe un bundle que no es nulo estamos en modo edicion
        if (DependencyManageFragmentArgs.fromBundle(getArguments()).getDependency() != null){
            viewModel = new ViewModelProvider(this, new DependencyManageViewModelFactory(DependencyManageFragmentArgs.fromBundle(getArguments()).getDependency())).get(DependencyManageVM.class);
            //binding.setDependency(DependencyManageFragmentArgs.fromBundle(getArguments()).getDependency());
            getActivity().setTitle(getString(R.string.strEditarDependencia));
            //setFabEditMode();
            initView(DependencyManageFragmentArgs.fromBundle(getArguments()).getDependency());
        }
        //Modo Añadir
        else{
            viewModel = new ViewModelProvider(this, null).get(DependencyManageVM.class);
            getActivity().setTitle(getString(R.string.strAddDependencia));
            //setFabAddMode();
        }
        viewModel.getState().observe(getViewLifecycleOwner(), object ->{
            StateManage stateManage = ((StateManage)object);
            switch (stateManage.getState()) {
                case SUCCESSADD:
                    onAddSuccess("ADD SUCCESS");
                    break;
                case SUCESSEDIT:
                    onAddSuccess("EDIT SUCCESS");
                    break;
                case FAILURE:
                    onAddFailure("ADD FAILURE");
                    break;
            }
        });
        binding.setViewmodel(viewModel);
    }

    private void setFabAddMode() {
        binding.fabManageDependency.setOnClickListener(v -> {
            presenter.add(getDependency());
        });
    }

    private Dependency getDependency() {
        Dependency dependency = new Dependency();
        if (DependencyManageFragmentArgs.fromBundle(getArguments()).getDependency() != null) {
            dependency.setId(DependencyManageFragmentArgs.fromBundle(getArguments()).getDependency().getId());
        }
        dependency.setNombre(binding.tiledtNombre.getText().toString());
        dependency.setNombreCorto(binding.tiledtNombreCorto.getText().toString());
        dependency.setDescripcion(binding.tiledtDescripcion.getText().toString());
        dependency.setImagen(binding.tiledtDescripcion.getText().toString());
        return dependency;
    }

    private void initView(Dependency dependency) {
        binding.tiledtNombre.setText(dependency.getNombre());
        binding.tiledtNombreCorto.setText(dependency.getNombreCorto());
        binding.tiledtDescripcion.setText(dependency.getDescripcion());
    }

    private void setFabEditMode() {
        binding.fabManageDependency.setImageResource(R.mipmap.update);
        binding.tiledtNombreCorto.setEnabled(false);
        binding.fabManageDependency.setOnClickListener( v ->{
            presenter.edit(DependencyManageFragmentArgs.fromBundle(getArguments()).getDependency().getNombreCorto(), getDependency());
        });
    }

    @Override
    public void onAddSuccess(String message) {
        //Ejemplo de notificacion (hay que crear el canal de notificacion antes de esto)
        //1- Crear un bundle para pasarle un objeto a la notificacion
        Bundle bundle = new Bundle();
        bundle.putSerializable(Dependency.TAG, getDependency());
        //2- Crear un pendingIntent y pasarle el grafo de navegacion, el destino, el argument...
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.dependencyManageFragment)
                .setArguments(bundle)
                .createPendingIntent();
        //3- Crear la notificacion y setear lo que necesitemos(Titulo, texto, imagen...)
        Notification.Builder builder = new Notification.Builder(getActivity(), InventoryApplication.IDCHANNEL)
                .setSmallIcon(R.drawable.inventory)
                .setContentTitle(getString(R.string.strAddDependencia))
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        //4- Añadir la notificacion al notificationManager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000), builder.build());
        NavHostFragment.findNavController(this).navigateUp();

    }

    public void fabClick(){
        Log.d("FABCLICK", "dependency");
    }

    @Override
    public void onAddFailure(String message) {

    }

    @Override
    public void onEditSuccess(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onEditFailure(String message) {

    }
}