package com.example.alarmmanagerguide

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("EXTRA_MESSAGE")

        val alarmIntent = Intent(context, AlarmActivity::class.java)
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("title")
            .setContentText("text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setFullScreenIntent(pendingIntent, true)



        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(1, builder.build())




    }
}