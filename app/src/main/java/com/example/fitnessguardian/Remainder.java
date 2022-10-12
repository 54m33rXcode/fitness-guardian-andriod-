package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fitnessguardian.databinding.ActivityRemainderBinding;

import java.util.Calendar;

public class Remainder extends DrawerBase implements View.OnClickListener{
    private int notificationId = 1;
    ActivityRemainderBinding remainderBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remainderBinding = ActivityRemainderBinding.inflate(getLayoutInflater());
        setContentView(remainderBinding.getRoot());
        allocateActivityTitle("Remainder");

        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        TimePicker timePicker = findViewById(R.id.timePicker);


        Intent intent = new Intent(Remainder.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);



        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                Remainder.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );


        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();


                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}



