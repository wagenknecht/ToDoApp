package de.wagenknecht.todoapp;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

//Diese Klasse nutzt Gestures und realisiert die Callbacks zur Behandlung von Gesten
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private TodoListAdapter mTodoListAdapter;


    public SwipeToDeleteCallback(TodoListAdapter todoListAdapter) {
        //Hier können wir entscheiden welche Swipes wir zulassen. Aktuell ist
        //dies nur in eine Richtung erlauibt. Für beide Richtungen den auskommentierten
        //Teil hinzufügen und dann in onSwiped() per switch case unterscheiden
        super(0, ItemTouchHelper.LEFT ); //| ItemTouchHelper.RIGHT);
        mTodoListAdapter = todoListAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    //Direction sagt uns in welche Richtung wir swipen:
    //LEFT, RIGHT, START, END, UP, DOWN
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getBindingAdapterPosition();

        switch(direction) {
            case ItemTouchHelper.LEFT:
//                mTodoListAdapter.remove(position);
                break;
            case ItemTouchHelper.RIGHT:
                //mAdapter.remove(position);
                break;
        }
    }
}