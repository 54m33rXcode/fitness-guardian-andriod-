package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Leg extends AppCompatActivity implements View.OnClickListener {
    ImageView ic,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leg);

        Button button1 = findViewById(R.id.btn_squat);
        Button button2 = findViewById(R.id.btn_jumpSquat);
        Button button3 = findViewById(R.id.btn_lunges);
        Button button4 = findViewById(R.id.btn_rlunges);
        Button button5 = findViewById(R.id.btn_glute);
        Button button6 = findViewById(R.id.btn_footer);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        back = findViewById(R.id.go_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_squat:
                Dialog dialog1 = new Dialog(this);
                dialog1.setContentView(R.layout.squat_layout);
                dialog1.show();

                ic =dialog1.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });
                break;

            case R.id.btn_jumpSquat:
                Dialog dialog2 = new Dialog(this);
                dialog2.setContentView(R.layout.jumpsquat_layout);
                dialog2.show();

                ic = dialog2.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
                break;

            case R.id.btn_lunges:
                Dialog dialog3 = new Dialog(this);
                dialog3.setContentView(R.layout.lunges_layout);
                dialog3.show();

                ic = dialog3.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog3.dismiss();
                    }
                });
                break;

            case R.id.btn_rlunges:
                Dialog dialog4 = new Dialog(this);
                dialog4.setContentView(R.layout.reverselunges_layout);
                dialog4.show();

                ic = dialog4.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog4.dismiss();
                    }
                });

                break;

            case R.id.btn_glute:
                Dialog dialog5 = new Dialog(this);
                dialog5.setContentView(R.layout.glutesbridge_layout);
                dialog5.show();

                ic = dialog5.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog5.dismiss();
                    }
                });
                break;


            case R.id.btn_footer:
                Intent intent = new Intent(Leg.this, Legex1.class);
                startActivity(intent);
                break;
        }
    }
}