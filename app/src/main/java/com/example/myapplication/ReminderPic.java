package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReminderPic extends AppCompatActivity implements View.OnClickListener {

    private int reminderValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_pic);
        findViewById(R.id.remindoButton).setOnClickListener(this);
        findViewById(R.id.unRemindoButton).setOnClickListener(this);
    }
    public void onClick(View view) {
        EditText reminderText = findViewById(R.id.RemindoText);
        TimePicker remindTimePick = findViewById(R.id.timePicker);

        Intent intent = new Intent(ReminderPic.this, ReminderReceive.class);
        intent.putExtra("Reminder Value", reminderValue);
        intent.putExtra("Task", reminderText.getText().toString());

        PendingIntent reminderIntent = PendingIntent.getBroadcast(ReminderPic.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch(view.getId()) {
            case R.id.remindoButton:
                int hour = remindTimePick.getCurrentHour();
                int minute = remindTimePick.getCurrentMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, reminderIntent);
                Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.unRemindoButton:
                alarm.cancel(reminderIntent);
                Toast.makeText(this, "Rekt!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}