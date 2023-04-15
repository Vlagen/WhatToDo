package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button add;
    private EditText text;
    private RadioGroup type;
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter todoAdapter;
    private static RecyclerView.LayoutManager layoutManager;
    private static List<Todo> todos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=(Button) findViewById(R.id.add);
        text=(EditText) findViewById(R.id.text);
        type=(RadioGroup) findViewById(R.id.type);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int select=type.getCheckedRadioButtonId();
                final RadioButton rb=(RadioButton) findViewById(select);

                Database db=new Database(MainActivity.this);
                db.addTodo(new Todo(text.getText().toString(),rb.getText().toString()));
                text.setText("");
                updateList();
            }
        });


    }

    public void updateList(){
        Database db=new Database(this);
        todos=db.getTodos();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        todoAdapter=new ToDoAdapter(MainActivity.this,todos);
        recyclerView.setAdapter(todoAdapter);
    }

    public void updateWhenDone(){
        todoAdapter=new ToDoAdapter(MainActivity.this,todos);
        recyclerView.setAdapter(todoAdapter);
    }


}