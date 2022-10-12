package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnessguardian.databinding.LogoutBinding;

public class Logout extends DrawerBase {
    LogoutBinding logoutBinding;

    SessionManager sessionManager;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logoutBinding = LogoutBinding.inflate(getLayoutInflater());
        setContentView(logoutBinding.getRoot());
        allocateActivityTitle("Logout");


        btn_logout = findViewById(R.id.btn_logout);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });
    }
}