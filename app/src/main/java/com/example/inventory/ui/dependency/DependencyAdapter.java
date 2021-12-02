package com.example.inventory.ui.dependency;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    private ArrayList<Dependency> list;
    private OnManageDependencyListener listener;

    public void update(List<Dependency> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnManageDependencyListener{
        void onEditDependency(Dependency dependency);
        void onDeleteDependency(Dependency dependency);
    }

    public DependencyAdapter(ArrayList<Dependency> list, OnManageDependencyListener listener ) {
        this.list = list;
        this.listener = listener;
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
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(list.get(position).getNombre().substring(0,1), Color.RED);
        holder.ivIcon.setImageDrawable(drawable);
        holder.tvNombre.setText(list.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        ImageView ivIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreDependencia);
            ivIcon = itemView.findViewById(R.id.ivDependencyItem);
        }
    }
}
