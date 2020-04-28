package com.example.shopapp.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.shopapp.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        displayNotification(context, intent.getStringExtra("message"));
    }

    private void displayNotification(Context context, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "cn1")
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(message)
                .setSmallIcon(R.drawable.baseline_home_black_18dp)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(1, builder.build());
    }

}

