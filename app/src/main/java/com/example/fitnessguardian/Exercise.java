package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.fitnessguardian.databinding.ExerciseBinding;

public class Exercise extends DrawerBase {
    ExerciseBinding exerciseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exerciseBinding = ExerciseBinding.inflate(getLayoutInflater());
        setContentView(exerciseBinding.getRoot());
        allocateActivityTitle("Exercise");

    }

    public void chest(View view) {
        Intent intent = new Intent(Exercise.this,Chest.class);
        startActivity(intent);
    }

    public void abs(View view) {
        Intent intent = new Intent(Exercise.this,Abs.class);
        startActivity(intent);
    }

    public void shoulder(View view) {
        Intent intent = new Intent(Exercise.this,Shoulder.class);
        startActivity(intent);
    }

    public void leg(View view) {
        Intent intent = new Intent(Exercise.this,Leg.class);
        startActivity(intent);
    }
}