package de.wagenknecht.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import java.util.List;

import de.wagenknecht.todoapp.AppDatabase;
import de.wagenknecht.todoapp.MainActivity;
import de.wagenknecht.todoapp.R;
import de.wagenknecht.todoapp.entity.Priority;

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
        holder.deletePriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Priority priority = priorityList.get(position);
                AppDatabase database = AppDatabase.getDatabase(context);
                database.priorityDao().removePriority(priority.priority_id);
                priorityList.remove(priority);
                notifyItemRemoved(position);
            }
        });
        holder.saveEditPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Priority priority = priorityList.get(position);
                priority.priority_name = holder.priorityName.getText().toString();
                AppDatabase database = AppDatabase.getDatabase(context);
                database.priorityDao().addPriority(priority);


            }
        });
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

        EditText priorityName;
        ImageButton deletePriority;
        ImageButton saveEditPriority;

        public myViewHolder(View itemView) {
            super(itemView);

            priorityName = itemView.findViewById(R.id.priorityName);
            deletePriority = itemView.findViewById(R.id.deletePriority);
            saveEditPriority = itemView.findViewById(R.id.saveEditPriority);
        }
    }
}




