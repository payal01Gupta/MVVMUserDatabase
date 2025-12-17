package com.demoapp.demoappbox.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demoapp.demoappbox.model.User;
import com.demoapp.demoappbox.ui.repositories.UserRepository;

import java.util.List;

public class UserViewModelFirebase extends AndroidViewModel {
    private UserRepository repository;
    private MutableLiveData<List<User>> userList;

    public UserViewModelFirebase(@NonNull Application application) {
        super(application);
        repository = new UserRepository();
        userList = new MutableLiveData<>();
        repository.getUsers(userList);
    }

    public LiveData<List<User>> getUsers() {
        return userList;
    }

    public void insert(User user) {
        repository.insert(user);
    }
}
