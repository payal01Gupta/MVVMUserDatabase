package com.vpn.myapplicationone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.vpn.myapplicationone.R;
import com.vpn.myapplicationone.model.User;
import com.vpn.myapplicationone.viewmodel.UserViewModel;

public class CreateUserActivity extends AppCompatActivity {
    EditText etName, etRoll, etSubject;
    Button btnSave, btnShow;
    UserViewModel viewModel;
    int userId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        etName = findViewById(R.id.etName);
        etRoll = findViewById(R.id.etRoll);
        etSubject = findViewById(R.id.etSubject);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        if (getIntent().hasExtra("id")) {
            userId = getIntent().getIntExtra("id", -1);
            etName.setText(getIntent().getStringExtra("name"));
            etRoll.setText(getIntent().getStringExtra("roll"));
            etSubject.setText(getIntent().getStringExtra("subject"));
            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String roll = etRoll.getText().toString();
                String subject = etSubject.getText().toString();

                if (userId == -1) {
                    viewModel.insert(new User(0, name, roll, subject));
                    Toast.makeText(CreateUserActivity.this, "User Added", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etRoll.setText("");
                    etSubject.setText("");
                } else {
                    viewModel.update(new User(userId, name, roll, subject));
                    Toast.makeText(CreateUserActivity.this, "User Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateUserActivity.this,ListUserActivity.class));
            }
        });
    }
}