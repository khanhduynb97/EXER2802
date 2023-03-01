package com.example.exer2802.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Username")
    public String username;

    @ColumnInfo(name = "Gender")
    public String gender;

    @ColumnInfo(name = "Description")
    public String desc;
}
