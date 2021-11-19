package com.example.inventory.ui.dependency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;

import java.util.ArrayList;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    private ArrayList<Dependency> list;

    public DependencyAdapter() {
        this.list = new ArrayList<Dependency>();
        list.add(new Dependency("Aula 1", "A1", null, null));
        list.add(new Dependency("Aula 2", "A2", null, null));
        list.add(new Dependency("Aula 3", "A3", null, null));
        list.add(new Dependency("Aula 4", "A4", null, null));
        list.add(new Dependency("Aula 5", "A5", null, null));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Asigna el item layout al viewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNombre.setText(list.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreDependencia);
        }
    }
}
