package com.example.builder_task.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.builder_task.MainActivityJava;
import com.example.builder_task.model.UserListModel;
import com.example.builder_task.repository.ListRepository;

import java.util.ArrayList;

public class MyViewModel extends AndroidViewModel {

    private final ListRepository repository ;


    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new ListRepository(application);

    }
    public MutableLiveData<ArrayList<UserListModel>> loadData(Context context) {
        return repository.callAPI(context);
    }
}
