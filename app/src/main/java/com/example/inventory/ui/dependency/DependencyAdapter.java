package com.example.inventory.ui.dependency;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.DependencyComparator;
import com.example.inventory.databinding.DependencyItemBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    private ArrayList<Dependency> list;
    private OnManageDependencyListener listener;
    private DependencyItemBinding mBinding;

//region Metodos que debe realizar el adapter
    public void update(List<Dependency> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void order(){
        Collections.sort(list);
        notifyDataSetChanged();
    }

    public void inverseOrder(){
        Collections.reverse(list);
        notifyDataSetChanged();
    }

    public void orderByDescripcion(){
        Collections.sort(list, new DependencyComparator());
        notifyDataSetChanged();
    }

    public void delete(Dependency deletedDependency){
        this.list.remove(deletedDependency);
        notifyDataSetChanged();
    }

    public void undo(Dependency dependency){
        list.add(dependency);
        notifyDataSetChanged();
    }
    //endregion

    //Respuestas de los eventos click de los elementos
    public interface OnManageDependencyListener{
        void onEditDependency(Dependency dependency);
        void onDeleteDependency(Dependency dependency);
    }

    public DependencyAdapter(ArrayList<Dependency> list, OnManageDependencyListener listener ) {
        this.list = list;
        this.listener = listener;
    }

    public DependencyAdapter(ArrayList<Dependency> list, OnManageDependencyListener listener, DependencyItemBinding binding) {
        mBinding = binding;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Asigna el item layout al viewHolder
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, parent, false);
        //return new ViewHolder(view);
        DependencyItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.dependency_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(list.get(position).getNombre().substring(0,1), color);
        //holder.ivIcon.setImageDrawable(drawable);
        //holder.tvNombre.setText(list.get(position).getNombre());
        //Opcion 2: Cada elemento de la lista indica a la clase holder que objeto es y quien es su listener
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        ImageView ivIcon;
        public ViewHolder(@NonNull DependencyItemBinding binding) {
        //public ViewHolder(@NonNull View itemView) {
            super(binding.getRoot());
            /**opcion 1 solo controlo el evento click(No se que elemento estoy pulsando)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditDependency("Se ha pulsado sobre un elemento");
                }
            });**/
            mBinding = binding;
            //tvNombre = itemView.findViewById(R.id.tvNombreDependencia);
            //ivIcon = itemView.findViewById(R.id.ivDependencyItem);
        }


        public void bind(Dependency dependency, OnManageDependencyListener listener) {
            mBinding.setDependency(dependency);
            //itemview es un elemento final de la clase padre visible desde toda la clase ViewHolder
            itemView.setOnClickListener(v->{
            listener.onEditDependency(dependency);
            });
            //Si consumo el evento devuelvo true, si no, false
            itemView.setOnLongClickListener(v ->{
                listener.onDeleteDependency(dependency);
                return true;
            });
        }
    }
}
