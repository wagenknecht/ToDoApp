package de.wagenknecht.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<viewHolder>{

    List<String> items;

    public Adapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new viewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class viewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    private Adapter adapter;

    public viewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textItem);
        itemView.findViewById(R.id.delete).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public viewHolder linkAdapter(Adapter adapter){
        this.adapter = adapter;
        return this;
    }
}


