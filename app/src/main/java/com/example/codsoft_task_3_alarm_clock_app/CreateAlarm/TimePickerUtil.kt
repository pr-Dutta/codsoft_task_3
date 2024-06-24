package com.example.codsoft_task_3_alarm_clock_app.CreateAlarm

import android.os.Build
import android.widget.TimePicker

class TimePickerUtil {
    companion object {
        fun getHour(timePicker: TimePicker) : Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour
            }else {
                timePicker.currentHour
            }
        }

        fun getMinute(timePicker: TimePicker) : Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.minute
            }else {
                timePicker.currentMinute
            }
        }


        fun setHour(timePicker: TimePicker, hour: Int) {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour = hour
            }else {
                timePicker.currentHour = hour
            }
        }

        fun setMinute(timePicker: TimePicker, minute: Int) {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.minute = minute
            }else {
                timePicker.currentMinute = minute
            }
        }
    }
}