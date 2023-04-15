package com.example.whattodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "todo.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE TODOS (ID INTEGER PRIMARY KEY AUTOINCREMENT, T TEXT,TYPE TEXT)";
        db.execSQL(createTable);
        }

        public void addTodo(Todo todo) {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv=new ContentValues();

            cv.put("T",todo.getText());
            cv.put("TYPE",todo.getType());

            db.insert("TODOS",null,cv);
        }

    public void removeTodo(int id){
        String query="DELETE FROM TODOS WHERE ID="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(query);

    }

    public List<Todo> getTodos(){
        List<Todo> resultList=new ArrayList<>();

        String queryList="SELECT * FROM TODOS ";
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(queryList,null);

        if(cursor.moveToFirst()){
            do {
                int id=cursor.getInt(0);
                String text=cursor.getString(1);
                String type=cursor.getString(2);

                Todo todo=new Todo(id,text,type);
                resultList.add(todo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

