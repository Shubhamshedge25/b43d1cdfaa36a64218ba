package com.example.builder_task;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.builder_task.adapter.UserListAdapter;
import com.example.builder_task.model.UserListModel;
import com.example.builder_task.viewmodel.MyViewModel;

import java.util.ArrayList;

public class MainActivityJava extends AppCompatActivity  {

    UserListAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<UserListModel> modelRecyclerArrayList;
    MyViewModel myViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        modelRecyclerArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        initRecycler();

        myViewModel.loadData(this).observe(this, new Observer<ArrayList<UserListModel>>() {
            @Override
            public void onChanged(ArrayList<UserListModel> countries) {
                if(countries!=null){
                    modelRecyclerArrayList = countries;
                    adapter.updateList(modelRecyclerArrayList);
                }
            }
        });
    }


    private void initRecycler() {
        adapter = new UserListAdapter(modelRecyclerArrayList, MainActivityJava.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

}