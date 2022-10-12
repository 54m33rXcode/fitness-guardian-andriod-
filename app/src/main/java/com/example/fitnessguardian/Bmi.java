package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnessguardian.databinding.BmiBinding;

public class Bmi extends DrawerBase {
BmiBinding bmiBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bmiBinding = BmiBinding.inflate(getLayoutInflater());
        setContentView(bmiBinding.getRoot());
        allocateActivityTitle("BMI Calculator");

        TextView txtResult;
        EditText edtWeight,edtHeight,edtHtIn;
        Button btnCalculate;

        edtWeight = findViewById(R.id.weight);
        edtHeight = findViewById(R.id.feet);
        edtHtIn = findViewById(R.id.inch);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult=findViewById(R.id.result);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtWeight.length() == 0) {
                    edtWeight.setError("without height BMI cannot be calculated");
                }

                else if (edtHeight.length() == 0) {
                    edtHeight.setError("without height BMI cannot be calculated");
                }

                else if (edtHtIn.length() == 0) {
                    edtHtIn.setError("without height BMI cannot be calculated");
                }

                else {
                    int wt = Integer.parseInt(edtWeight.getText().toString());
                    int ft = Integer.parseInt(edtHeight.getText().toString());
                    int in = Integer.parseInt(edtHtIn.getText().toString());

                    int totalIn = ft * 12 + in;

                    double totalCm = totalIn * 2.53;
                    double totalM = totalCm / 100;
                    double bmi = (wt / (totalM * totalM));
                    String result = String.format("%.2f", bmi);


                    if (bmi > 25) {
                        txtResult.setText("you are overweight \n your BMI is " + result);
                    }

                    else if (bmi < 18) {
                        txtResult.setText("you are under weight \n your BMI is " + result);
                    }

                    else {
                        txtResult.setText("you are healthy \n your BMI is " + result);
                    }
                }
            }
        });
    }
}