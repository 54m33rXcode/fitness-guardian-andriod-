package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Abs extends AppCompatActivity  implements View.OnClickListener {
    ImageView ic,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abs);


        Button button1 = findViewById(R.id.btn_abdominal);
        Button button2 = findViewById(R.id.btn_bicycle);
        Button button3 = findViewById(R.id.btn_mountain);
        Button button4 = findViewById(R.id.btn_vup);
        Button button5 = findViewById(R.id.btn_crossover);
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
        switch (view.getId()){
            case R.id.btn_abdominal:
                Dialog dialog1= new Dialog(this);
                dialog1.setContentView(R.layout.abdominal_layout);
                dialog1.show();

                ic = dialog1.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });
                break;

            case R.id.btn_bicycle:
                Dialog dialog2= new Dialog(this);
                dialog2.setContentView(R.layout.bicycle_layout);
                dialog2.show();

                ic = dialog2.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
                break;

            case R.id.btn_mountain:
                Dialog dialog3= new Dialog(this);
                dialog3.setContentView(R.layout.mountain_layout);
                dialog3.show();

                ic = dialog3.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog3.dismiss();
                    }
                });
                break;

            case R.id.btn_vup:
                Dialog dialog4= new Dialog(this);
                dialog4.setContentView(R.layout.vup_layout);
                dialog4.show();

                ic = dialog4.findViewById(R.id.close);
                ic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog4.dismiss();
                    }
                });
                break;

            case R.id.btn_crossover:
                Dialog dialog5= new Dialog(this);
                dialog5.setContentView(R.layout.crossover_layout);
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
                Intent intent = new Intent(Abs.this,Abex1.class);
                startActivity(intent);
                break;

        }
    }
}