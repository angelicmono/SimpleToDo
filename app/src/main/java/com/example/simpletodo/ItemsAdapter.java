package com.example.simpletodo;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public ItemsAdapter(List<String> items) {

    }

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflater to inflate a view

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }
    // responsible for binding data to a particular view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the position
        String item = items.get(position);
        //Bind the item into the specified View Holder
        holder.bind(item);

    }
    // Tells the RV how many items are in the list
    @Override
    public int getItemCount() {

        return items.size();
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItems = itemView.findViewById(android.R.id.text1);
        }
        //Update the view inside the view holder with this data
        public void bind(String item) {
            tvItems.setText(item);
            tvItems.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Notify the listener which position was long pressed.
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
