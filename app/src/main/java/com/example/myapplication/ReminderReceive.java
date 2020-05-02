package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReminderReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int remindId = intent.getIntExtra("Reminder Value", 0);
        String reminder = intent.getStringExtra("Task");
        Intent main = new Intent(context, MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(context, 0, main, 0);
        NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Remind0").setContentText(reminder)
                .setWhen(System.currentTimeMillis()).setAutoCancel(true)
                .setContentIntent(pending);
        notifManager.notify(remindId, builder.build());

    }
}
