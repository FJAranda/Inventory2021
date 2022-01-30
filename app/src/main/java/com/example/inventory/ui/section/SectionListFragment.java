package com.example.inventory.ui.section;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.base.BaseDialogFragment;
import com.example.inventory.data.model.Section;
import com.example.inventory.databinding.FragmentSectionListBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SectionListFragment extends Fragment implements SectionListContract.View, SectionAdapter.SectionListListener {
    FragmentSectionListBinding binding;
    SectionListPresenter presenter;
    SectionAdapter adapter;
    Section deleted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new SectionListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSectionListBinding.inflate(inflater);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRVSections();
        initFab();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    private void initFab() {
        binding.fabSectionList.setOnClickListener(v ->{
            SectionListFragmentDirections.ActionSectionListFragmentToSectionManageFragment action =
                    SectionListFragmentDirections.actionSectionListFragmentToSectionManageFragment(null);
            NavHostFragment.findNavController(this).navigate(action);

        });
    }

    private void initRVSections() {
        adapter = new SectionAdapter(new ArrayList<>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvSections.setLayoutManager(linearLayoutManager);
        binding.rvSections.setAdapter(adapter);
    }


    @Override
    public void showProgress() {
        binding.includePBSectionList.llPBDLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.includePBSectionList.llPBDLayout.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<Section> list) {
        adapter.update(list);
    }

    @Override
    public void onFailure() {
        Snackbar.make(getView(), getString(R.string.strErrorLoading), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSuccess(Section section) {
        adapter.delete(deleted);
        Snackbar.make(getView(), deleted.getNombreCorto() + "Eliminada", BaseTransientBottomBar.LENGTH_SHORT)
                .setAction(getString(R.string.strUndo), v ->{
                    presenter.undo(deleted);
                }).show();
    }

    @Override
    public void onDeleteFailure(Section section) {
        Snackbar.make(getView(), getString(R.string.strErrorDeleting), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUndoSuccess(Section section) {
        adapter.undo(deleted);
    }

    @Override
    public void onUndoFailure(Section section) {
        Snackbar.make(getView(), getString(R.string.strErrorUndoing), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEditSection(Section section) {
        SectionListFragmentDirections.ActionSectionListFragmentToSectionManageFragment action =
                SectionListFragmentDirections.actionSectionListFragmentToSectionManageFragment(section);
        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDeleteSection(Section section) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, "Elimminar Elemento");
        bundle.putString(BaseDialogFragment.MESSAGE, "¿Desea eliminar el elemento: " + section.getNombre() + "?");
        //Conectar el dialogFragment en el grefico de navegacion para poder navegar
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Si la respuesta es true, se realiza lo que programemos aqui
                if (result.getBoolean(BaseDialogFragment.KEY_BUNDLE)){
                    deleted = section;
                    presenter.delete(section);
                }
            }
        });
        NavHostFragment.findNavController(this).navigate(R.id.action_sectionList_to_baseDialogFragment, bundle);
    }
}