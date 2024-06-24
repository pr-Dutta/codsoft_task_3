package com.example.codsoft_task_3_alarm_clock_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.codsoft_task_3_alarm_clock_app.Service.AlarmService
import com.example.codsoft_task_3_alarm_clock_app.databinding.ActivityRingAlarmBinding

class RingAlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRingAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingAlarmBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

         binding.btnClose.setOnClickListener {
             val intent = Intent(this, AlarmService::class.java)
             applicationContext.stopService(intent)
             finish()
         }
    }
}