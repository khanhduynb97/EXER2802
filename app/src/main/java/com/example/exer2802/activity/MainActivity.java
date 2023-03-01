package com.example.exer2802.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.exer2802.R;
import com.example.exer2802.adapter.UserAdapter;
import com.example.exer2802.database.UserDatabase;
import com.example.exer2802.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvUser;
    UserDatabase db;
    List<User> users;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = UserDatabase.getDbInstance(this);

        users = db.userDao().getAllUsers();

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUser.setLayoutManager(layout);

        adapter = new UserAdapter(this, users);
        rcvUser.setAdapter(adapter);
    }
}