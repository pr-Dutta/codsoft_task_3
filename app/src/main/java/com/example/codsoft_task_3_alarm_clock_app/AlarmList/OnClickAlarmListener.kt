package com.example.codsoft_task_3_alarm_clock_app.AlarmList

import com.example.codsoft_task_3_alarm_clock_app.Model.AlarmItem

interface OnClickAlarmListener {

    fun onClick(alarmItem: AlarmItem)

    fun onLongClick(alarmItem: AlarmItem)
}