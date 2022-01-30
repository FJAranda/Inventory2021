package com.example.inventory.ui.section;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    public ArrayList<Section> list;
    private SectionListListener listener;

    public interface SectionListListener{
        void onEditSection(Section section);
        void onDeleteSection(Section section);
    }

    public SectionAdapter(ArrayList<Section> list, SectionListListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void update(List<Section> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void delete(Section section){
        this.list.remove(section);
        notifyDataSetChanged();
    }

    public void undo(Section section){
        this.list.add(section);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.ViewHolder holder, int position) {
        //TODO: SET IMAGE
        //holder.ivSection.setImageDrawable(list.get(position).getImagen());
        holder.tvSection.setText(list.get(position).getNombreCorto());
        holder.tvDependency.setText(list.get(position).getDependencia());
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSection;
        TextView tvSection;
        TextView tvDependency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivSection = itemView.findViewById(R.id.ivSectionItem);
            this.tvSection = itemView.findViewById(R.id.tvSectionNombreCorto);
            this.tvDependency = itemView.findViewById(R.id.tvSectionDependency);
        }

        public void bind(Section section, SectionListListener listener){
            itemView.setOnClickListener(v ->{
                Log.d("SECTION ADAPTER", String.valueOf(section.getId()));
                listener.onEditSection(section);
            });
            itemView.setOnLongClickListener( v->{
                listener.onDeleteSection(section);
                return true;
            });
        }
    }
}
