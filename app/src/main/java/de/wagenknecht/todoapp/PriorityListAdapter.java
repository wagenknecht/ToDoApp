package de.wagenknecht.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PriorityListAdapter extends RecyclerView.Adapter<PriorityListAdapter.myViewHolder>{

    private Context context;
    private List<Priority> priorityList;

    public void setPriorityList(List<Priority> priorityList) {
        this.priorityList = priorityList;
        notifyDataSetChanged();
    }

    public PriorityListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_priority, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.priorityName.setText(priorityList.get(position).priority_name);
    }

    @Override
    public int getItemCount() {
        return priorityList.size();
    }

//    public void remove(int position) {
//        items.remove(position);
//        notifyItemRemoved(position);
//    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView priorityName;

        public myViewHolder(View itemView) {
            super(itemView);

            priorityName = itemView.findViewById(R.id.priorityName);
        }
    }
}




