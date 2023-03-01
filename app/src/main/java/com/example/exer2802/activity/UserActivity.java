package com.example.exer2802.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exer2802.R;
import com.example.exer2802.database.UserDatabase;
import com.example.exer2802.entity.User;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUser, edtDesc;
    Spinner spGender;
    CheckBox cbRules;
    Button btnRegister;
    String gender = "Male";
    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        db = UserDatabase.getDbInstance(this);

        edtUser = findViewById(R.id.edtUser);
        edtDesc = findViewById(R.id.edtDesc);
        spGender = findViewById(R.id.spGender);
        cbRules = findViewById(R.id.cbRules);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);

        String[] genders = {"Male", "Female", "Unknown"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        spGender.setAdapter(adapter);
        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemSelected: " + genders[position]);
                gender = genders[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                onRegister();
                break;
            default:
                break;
        }
    }

    private void onRegister() {
        if (!validate()) {
            return;
        }

        User user = new User();
        user.username = edtUser.getText().toString();
        user.gender = gender;
        user.desc = edtDesc.getText().toString();

        long id = db.userDao().insertUser(user);
        if (id > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }

        goToListUser();
    }

    private void goToListUser() {
        Intent intent = new Intent(UserActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean validate() {
        String message = null;
        if (edtUser.getText().toString().trim().isEmpty()) {
            message = "Yêu cầu nhập tên đăng nhập";
        } else if (edtDesc.getText().toString().trim().isEmpty()) {
            message = "Yêu cầu nhập phần giới thiệu";
        } else if (!cbRules.isChecked()) {
            message = "Yêu cầu đồng ý điều khoản sử dụng";
        }
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}