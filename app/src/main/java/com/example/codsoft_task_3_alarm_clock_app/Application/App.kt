package com.example.codsoft_task_3_alarm_clock_app.Application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class App : Application() {

    companion object {
        val ID = "com.example.codsoft_task_3_alarm_clock_app.pr-Dutta"
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    private fun createChannel() {
        val channel = NotificationChannel(
            ID,
            "Alarm Service",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}