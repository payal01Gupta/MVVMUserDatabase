package com.demoapp.demoappbox.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.demoapp.demoappbox.R;
import com.demoapp.demoappbox.model.User;
import com.demoapp.demoappbox.viewmodel.UserViewModelFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class CreateUserActivity extends AppCompatActivity {
    EditText etName, etRoll, etSubject;
    Button btnSave, btnShow;
    UserViewModelFirebase viewModel;
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

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            auth.signInAnonymously()
                    .addOnSuccessListener(result -> {
                        Log.e("AUTH", "Anonymous login success: " + result.getUser().getUid());
                    })
                    .addOnFailureListener(e -> {
                        Log.e("AUTH", "Auth failed: " + e.getMessage());
                    });
        }

        viewModel = new ViewModelProvider(this).get(UserViewModelFirebase.class);

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

                User user = new User(name, roll, subject);
                viewModel.insert(user);

                Toast.makeText(CreateUserActivity.this, "Saved to Firebase", Toast.LENGTH_SHORT).show();

//                if (userId == -1) {
//                    viewModel.insert(new User(0, name, roll, subject));
//                    Toast.makeText(CreateUserActivity.this, "User Added", Toast.LENGTH_SHORT).show();
//                    etName.setText("");
//                    etRoll.setText("");
//                    etSubject.setText("");
//                } else {
//                    viewModel.update(new User(userId, name, roll, subject));
//                    Toast.makeText(CreateUserActivity.this, "User Updated", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
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