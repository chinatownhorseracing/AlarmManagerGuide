package com.example.alarmmanagerguide

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.alarmmanagerguide.models.Person
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class AlarmApplication: Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Person::class
                )
            )
        )
    }

    fun createNotificationChannel() {
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