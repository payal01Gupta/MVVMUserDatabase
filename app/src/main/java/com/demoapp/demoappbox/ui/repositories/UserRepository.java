package com.demoapp.demoappbox.ui.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demoapp.demoappbox.database.UserDatabase;
import com.demoapp.demoappbox.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  //  private UserDatabase database;
    private final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    public void insert(String person, User user) {
        rootRef.child(person)
                .push()
                .setValue(user);
    }

    public LiveData<List<User>> getUsers(String person) {

        MutableLiveData<List<User>> liveData = new MutableLiveData<>();

        rootRef.child(person)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<User> list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            User user = ds.getValue(User.class);
                            list.add(user);
                        }
                        liveData.postValue(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });

        return liveData;
    }

    }

  /*  public UserRepository(Context context) {
        database = new UserDatabase(context);
    }

    public void insert(User user){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("roll", user.getRoll_number());
        cv.put("subject", user.getSubject());
        db.insert("users",null, cv);
    }

    public void update(User user){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",user.getName());
        cv.put("roll",user.getRoll_number());
        cv.put("subject",user.getSubject());
        db.update("users", cv, "id=?", new String[]{String.valueOf(user.getId())});
    }
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);

        while (cursor.moveToNext()) {
            list.add(new User(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));
        }
        cursor.close();
        return list;
    }*/
}
