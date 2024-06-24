package com.example.codsoft_task_3_alarm_clock_app.Model

import android.app.Application
import androidx.lifecycle.LiveData

class AlarmRepository {
    private var alarmDao: AlarmDao
    var list: LiveData<List<AlarmItem>>

    constructor(application: Application) {
        this.alarmDao = AlarmDatabase.getInstance(application).alarmDao()
        this.list = alarmDao.getAll()
    }

    suspend fun insert(alarmItem: AlarmItem) {
        alarmDao.insert(alarmItem)
    }

    suspend fun delete(alarmItem: AlarmItem) {
        alarmDao.delete(alarmItem)
    }

    suspend fun update(alarmItem: AlarmItem) {
        alarmDao.update(alarmItem)
    }
}





