package com.example.inventory.ui.section;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.inventory.InventoryApplication;
import com.example.inventory.R;
import com.example.inventory.data.database.MyDatabase;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.data.repository.DependencyRepositoryRoom;
import com.example.inventory.databinding.FragmentSectionManageBinding;
import com.example.inventory.ui.dependency.DependencyManageFragmentArgs;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

public class SectionManageFragment extends Fragment implements SectionManageContract.View{
    FragmentSectionManageBinding binding;
    SectionManagePresenter presenter;
    List<Dependency> dependencies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SectionManagePresenter(this);
        dependencies = DependencyRepositoryRoom.getInstance().getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSectionManageBinding.inflate(inflater);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tiledtNombreSection.addTextChangedListener(new ObjectTextWatcher(binding.tiledtNombreSection));
        binding.tiledtNombreCortoSection.addTextChangedListener(new ObjectTextWatcher(binding.tiledtNombreCortoSection));
        binding.tiledtDescripcionSection.addTextChangedListener(new ObjectTextWatcher(binding.tiledtDescripcionSection));
        if (SectionManageFragmentArgs.fromBundle(getArguments()).getSection() != null){
            getActivity().setTitle(getString(R.string.strEditarSection));
            setFabEditMode();
            setSpinner();
            initView(SectionManageFragmentArgs.fromBundle(getArguments()).getSection());
        }
        //Modo Añadir
        else{
            getActivity().setTitle(getString(R.string.strAddSection));
            setFabAddMode();
            setSpinner();
        }
    }

    private void initView(Section section) {
        //TODO: SETIMAGE
        //binding.ivSection.setImageDrawable(section.getImagen());
        binding.tiledtNombreCortoSection.setText(section.getNombreCorto());
        binding.tiledtNombreSection.setText(section.getNombre());
        binding.tiledtDescripcionSection.setText(section.getDescripcion());
        binding.spDependencias.setSelection(dependencies.indexOf(section.getDependencia()));
    }

    private void setSpinner() {
        ArrayAdapter<Dependency> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, dependencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spDependencias.setAdapter(adapter);
    }

    private void setFabAddMode() {
        if (binding.tilNombreCortoSection.getError() == null && binding.tilDescripcionSection.getError() == null && binding.tilNombreSection.getError() == null){
            binding.fabManageSection.setOnClickListener(v -> {
                if (binding.spDependencias.getSelectedItem() == null) {
                    Snackbar.make(getView(), getString(R.string.strDependecyNull), Snackbar.LENGTH_SHORT).show();
                }else {
                    presenter.add(getSection());
                }
            });
        }
    }

    private void setFabEditMode() {
        binding.fabManageSection.setImageResource(R.mipmap.update);
        binding.tiledtNombreCortoSection.setEnabled(false);
        binding.fabManageSection.setOnClickListener( v ->{
            presenter.edit(SectionManageFragmentArgs.fromBundle(getArguments()).getSection().getNombreCorto(), getSection());
        });
    }

    private Section getSection() {
        Section section = new Section();
        if (SectionManageFragmentArgs.fromBundle(getArguments()).getSection() != null) {
            section.setId(SectionManageFragmentArgs.fromBundle(getArguments()).getSection().getId());
        }
        section.setNombre(binding.tiledtNombreSection.getText().toString());
        section.setNombreCorto(binding.tiledtNombreCortoSection.getText().toString());
        section.setDescripcion(binding.tiledtDescripcionSection.getText().toString());
        section.setDependencia(binding.spDependencias.getSelectedItem().toString());
        //TODO: SET IMAGE
        section.setImagen(binding.ivSection.getDrawable().toString());
        return section;
    }

    @Override
    public void onAddSuccess(Section section) {
        //Ejemplo de notificacion (hay que crear el canal de notificacion antes de esto)
        //1- Crear un bundle para pasarle un objeto a la notificacion
        Bundle bundle = new Bundle();
        bundle.putSerializable(Section.TAG, getSection());
        //2- Crear un pendingIntent y pasarle el grafo de navegacion, el destino, el argument...
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.sectionManageFragment)
                .setArguments(bundle)
                .createPendingIntent();
        //3- Crear la notificacion y setear lo que necesitemos(Titulo, texto, imagen...)
        Notification.Builder builder = new Notification.Builder(getActivity(), InventoryApplication.IDCHANNEL)
                .setSmallIcon(R.drawable.inventory)
                .setContentTitle(getString(R.string.strAddSection))
                .setContentText("Nueva seccion añadida! Click aqui para navegar")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        //4- Añadir la notificacion al notificationManager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000), builder.build());
        //NavHostFragment.findNavController(this).navigateUp();
        Snackbar.make(getView(), getString(R.string.strAddSectionSuccess), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onAddFailure(Section section) {
        Snackbar.make(getView(), getString(R.string.strAddFailure), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEditSuccess(Section section) {
        Snackbar.make(getView(), getString(R.string.strEditarSectionSuccess), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEditFailure(Section section) {
        Snackbar.make(getView(), getString(R.string.strEditFailure), Snackbar.LENGTH_SHORT).show();
    }

    class ObjectTextWatcher implements TextWatcher {
        private View view;

        ObjectTextWatcher (View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()){
                case R.id.tiledtNombreCortoSection:
                    validateNombreCorto(s.toString());
                    break;
                case R.id.tiledtNombreSection:
                    validateNombre(s.toString());
                    break;
                case R.id.tiledtDescripcionSection:
                    validateDescription(s.toString());
                    break;
            }
        }

        private void validateDescription(String string) {
            if (string == null){
                binding.tilDescripcionSection.setError(getString(R.string.strNullValue));
            }else if(string.length() < 4){
                binding.tilDescripcionSection.setError(getString(R.string.strBadLength));
            }else{
                binding.tilDescripcionSection.setError(null);
            }
        }

        private void validateNombre(String string) {
            if (string == null){
                binding.tilNombreSection.setError(getString(R.string.strNullValue));
            }else if(string.length() < 4){
                binding.tilNombreSection.setError(getString(R.string.strBadLength));
            }else{
                binding.tilNombreSection.setError(null);
            }
        }

        private void validateNombreCorto(String string) {
            if (string == null){
                binding.tilNombreCortoSection.setError(getString(R.string.strNullValue));
            }else if(string.length() < 4){
                binding.tilNombreCortoSection.setError(getString(R.string.strBadLength));
            }else{
                binding.tilNombreCortoSection.setError(null);
            }
        }
    }

}