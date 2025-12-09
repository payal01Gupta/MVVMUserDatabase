package com.vpn.myapplicationone.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vpn.myapplicationone.R;
import com.vpn.myapplicationone.model.User;
import com.vpn.myapplicationone.ui.adapter.ListUserAdapter;
import com.vpn.myapplicationone.viewmodel.UserViewModel;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserViewModel userViewModel;
    ListUserAdapter adapter;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

            userViewModel.getUsers().observe(this, users -> {
            adapter = new ListUserAdapter(users, this::onUserLongClick);
            recyclerView.setAdapter(adapter);
        });
    }
    private void onUserLongClick(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Edit User")
                .setMessage("Do you want to edit this user?")
                .setPositiveButton("Edit", (dialog, which) -> openEdit(user))
                .setNegativeButton("Cancel", null)
                .show();
    }
    private void openEdit(User user) {
        Intent i = new Intent(this, CreateUserActivity.class);
        i.putExtra("id", user.getId());
        i.putExtra("name", user.getName());
        i.putExtra("roll", user.getRoll_number());
        i.putExtra("subject", user.getSubject());
        startActivity(i);
    }
}