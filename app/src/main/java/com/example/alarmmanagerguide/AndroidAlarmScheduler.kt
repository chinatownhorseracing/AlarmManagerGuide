package com.example.alarmmanagerguide

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.ZoneId

class AndroidAlarmScheduler(
    val context: Context
) {
    val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun schedule(item: AlarmItem) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("EXTRA_MESSAGE", item.message)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}