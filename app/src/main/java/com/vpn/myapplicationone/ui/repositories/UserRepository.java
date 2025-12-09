package com.vpn.myapplicationone.ui.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vpn.myapplicationone.database.UserDatabase;
import com.vpn.myapplicationone.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private UserDatabase database;

    public UserRepository(Context context) {
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
    }
}
