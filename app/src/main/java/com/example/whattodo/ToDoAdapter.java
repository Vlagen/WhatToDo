package com.example.whattodo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoCardHolder>{
    private Context context;
    private List<Todo> todos;

    public ToDoAdapter(Context context, List<Todo> todos){
        this.todos=todos;
        this.context=context;
    }

    @NonNull
    @Override
    public ToDoCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.todo,null,false);
        RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(params);

        ToDoCardHolder rcv=new ToDoCardHolder((layoutView));
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoCardHolder holder, int position) {
        Database db=new Database(holder.itemView.getContext());

        holder.text.setText(todos.get(position).getText());
        holder.type.setText(todos.get(position).getType());

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.removeTodo(todos.get(position).getId());
                holder.card.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = holder.card.getLayoutParams();
                params.height = 0;
                holder.card.setLayoutParams(params);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
