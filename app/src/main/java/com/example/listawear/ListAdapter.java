package com.example.listawear;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ItemsList> mItems = new ArrayList<>();
    private RecyclerViewClickListener listener;

    public ListAdapter(List<ItemsList> _items,RecyclerViewClickListener listener){
        mItems = _items;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView,textView2;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 =itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listener.onClick(itemView,getPosition());
        }
    }// class ViewHolder
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.main_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    } // ListAdapter.ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder, int i){
        ItemsList itemsList = mItems.get(i);
        viewHolder.imageView.setImageResource(itemsList.getImageItem());
        viewHolder.textView.setText(itemsList.getNameItem());
    }
    @Override
    public int getItemCount(){
        return mItems.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int i);
    }
}

class ItemsList implements Serializable {
    private int imageItem;
    private String nameItem;
    private String descripcion;

    public ItemsList(int _imageItem, String _nameItem,String _descripcion){
        this.imageItem = _imageItem;
        this.nameItem = _nameItem;
        this.descripcion = _descripcion;
    }

    public int getImageItem(){
        return imageItem;
    }
    public String getNameItem(){
        return nameItem;
    }
    public String getDescripcion() { return descripcion; }

}