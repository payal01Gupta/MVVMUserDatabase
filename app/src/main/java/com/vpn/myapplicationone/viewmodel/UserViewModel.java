package com.vpn.myapplicationone.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vpn.myapplicationone.model.User;
import com.vpn.myapplicationone.ui.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private MutableLiveData<List<User>> userList = new MutableLiveData<>();

    public UserViewModel(@NonNull Application app) {
        super(app);
        repository = new UserRepository(app);
        loadUsers();
    }

    public LiveData<List<User>> getUsers() {
        return userList;
    }

    public void loadUsers() {
        userList.setValue(repository.getUsers());
    }

    public void insert(User user) {
        repository.insert(user);
        loadUsers();
    }

    public void update(User user) {
        repository.update(user);
        loadUsers();
    }
}
