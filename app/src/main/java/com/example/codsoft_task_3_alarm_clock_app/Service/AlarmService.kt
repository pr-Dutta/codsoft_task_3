package com.example.codsoft_task_3_alarm_clock_app.Service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.codsoft_task_3_alarm_clock_app.Application.App
import com.example.codsoft_task_3_alarm_clock_app.R
import com.example.codsoft_task_3_alarm_clock_app.RingAlarmActivity

class AlarmService : Service() {

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val intent = Intent(this, RingAlarmActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0,
            intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, App.ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)             // here it_time icon should be set
            .setContentTitle("Wake Up")
            .setContentText("Please! Wake Up")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        return START_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}