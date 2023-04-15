package com.example.whattodo;

import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoCardHolder extends RecyclerView.ViewHolder   {

    public TextView text;
    public TextView type;
    public Button done;

    public LinearLayout card;

    public ToDoCardHolder(@NonNull View itemView) {
        super(itemView);

        text=(TextView) itemView.findViewById(R.id.text);
        type=(TextView) itemView.findViewById(R.id.type);
        done=(Button) itemView.findViewById(R.id.done);
        card=(LinearLayout) itemView.findViewById(R.id.card);
    }

}
