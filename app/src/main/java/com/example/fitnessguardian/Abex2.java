package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Abex2 extends AppCompatActivity {
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abex2);

        button2= findViewById(R.id.ex2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Abex2.this, Abex3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}