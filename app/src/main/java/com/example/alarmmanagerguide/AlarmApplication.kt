package com.example.alarmmanagerguide

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class AlarmApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = "Default Channel"
        val descriptionText = "Default notification channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channelId = "channel_id"

        val channel = NotificationChannel(
            channelId,
            name,
            importance
        )
        channel.description = descriptionText

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}