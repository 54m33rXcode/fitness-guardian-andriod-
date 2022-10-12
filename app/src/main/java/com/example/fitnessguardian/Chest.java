package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Chest extends AppCompatActivity implements View.OnClickListener {
    ImageView ic1,ic2,ic3,ic4,ic5,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chest);

        Button button1 = findViewById(R.id.btn_spider);
        Button button2 = findViewById(R.id.btn_knee);
        Button button3 = findViewById(R.id.btn_push);
        Button button4 = findViewById(R.id.btn_incline);
        Button button5 = findViewById(R.id.btn_decline);
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
            case R.id.btn_spider:
                Dialog dialog1= new Dialog(this);
                dialog1.setContentView(R.layout.spiderman_layout);
                dialog1.show();

                ic1 = dialog1.findViewById(R.id.close1);
                ic1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });
                break;

            case R.id.btn_knee:
                Dialog dialog2= new Dialog(this);
                dialog2.setContentView(R.layout.knee_dialog);
                dialog2.show();

                ic2 = dialog2.findViewById(R.id.close2);
                ic2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
                break;

            case R.id.btn_push:
                Dialog dialog3= new Dialog(this);
                dialog3.setContentView(R.layout.push_layout);
                dialog3.show();

                ic3 = dialog3.findViewById(R.id.close3);
                ic3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog3.dismiss();
                    }
                });
                break;

            case R.id.btn_incline:
                Dialog dialog4= new Dialog(this);
                dialog4.setContentView(R.layout.incline_layout);
                dialog4.show();

                ic4 = dialog4.findViewById(R.id.close4);
                ic4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog4.dismiss();
                    }
                });

                break;

            case R.id.btn_decline:
                Dialog dialog5= new Dialog(this);
                dialog5.setContentView(R.layout.decline_layout);
                dialog5.show();

                ic5 = dialog5.findViewById(R.id.close5);
                ic5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog5.dismiss();
                    }
                });

                break;

            case R.id.btn_footer:
                Intent intent = new Intent(Chest.this, Chestex1.class);
                startActivity(intent);
                break;

        }

    }

}

