package com.example.builder_task;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
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

    int page = 0;


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

        // adding on scroll change listener method for our nested scroll view.
        findViewById(R.id.recycler_view).setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

            }
        });
    }


    private void initRecycler() {
        adapter = new UserListAdapter(modelRecyclerArrayList, MainActivityJava.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }

}