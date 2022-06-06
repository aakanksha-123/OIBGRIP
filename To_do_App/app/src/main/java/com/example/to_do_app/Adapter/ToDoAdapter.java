package com.example.to_do_app.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do_app.AdapterNewTask;
import com.example.to_do_app.MainActivity;
import com.example.to_do_app.Model.ToDoModel;
import com.example.to_do_app.R;
import com.example.to_do_app.Utils.DataBaseHelper;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
private List<ToDoModel> mList;
private MainActivity activity;
private DataBaseHelper myDB;


public ToDoAdapter(DataBaseHelper myDB,MainActivity activity){
    this.activity=activity;
    this.myDB=myDB;
}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModel item = mList.get(position);
        holder.mCheckbox.setText(item.getTask());
        holder.mCheckbox.setChecked(toBoolean(item.getStatus()));
        holder.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    myDB.updateStatus(item.getId(),1);
                }else {
                    myDB.updateStatus(item.getId(),0);
                }
            }


        });
    }

    private boolean toBoolean(int status) {
        return false;
    }

   public Context getContext(){
    return activity;
   }

   public void setTask(List<ToDoModel> mList){
    this.mList = mList;
    notifyDataSetChanged();
   }
public void deleteTask(int position){
    ToDoModel item = mList.get(position);
    myDB.deleteTask(item.getId());
    mList.remove(position);
    notifyItemRemoved(position);
}


public void editItem(int position){
    ToDoModel item = mList.get(position);
    Bundle bundle = new Bundle();
    bundle.putInt("id",item.getId());
    bundle.putString("task",item.getTask());


    AdapterNewTask task= new AdapterNewTask();
    task.setArguments(bundle);
    task.show(activity.getSupportFragmentManager(),task.getTag());
}
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
     CheckBox mCheckbox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckbox = itemView.findViewById(R.id.mcheckbox);
        }
    }
}
