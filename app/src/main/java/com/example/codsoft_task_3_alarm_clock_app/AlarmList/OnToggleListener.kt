package com.example.codsoft_task_3_alarm_clock_app.AlarmList

import com.example.codsoft_task_3_alarm_clock_app.Model.AlarmItem

interface OnToggleListener {
    fun onToggle(alarmItem: AlarmItem)
}