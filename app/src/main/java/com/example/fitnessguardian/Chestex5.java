package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Chestex5 extends AppCompatActivity {
    Button button7,button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chestex5);

        button7 = findViewById(R.id.ex2);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                congoDialog();
            }
        });
    }

    private void congoDialog() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.congo_dialog);
        dialog.show();

        button = dialog.findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chestex5.this,Chest.class);
                startActivity(intent);
                finish();
            }
        });
    }
}