package com.example.codsoft_task_3_alarm_clock_app.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.codsoft_task_3_alarm_clock_app.Model.AlarmItem
import com.example.codsoft_task_3_alarm_clock_app.Model.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private var repository : AlarmRepository
    var list: LiveData<List<AlarmItem>>

    init {
        repository = AlarmRepository(application)
        list = repository.list
    }

    fun insert(alarmItem: AlarmItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(alarmItem)
        }
    }

    fun delete(alarmItem: AlarmItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(alarmItem)
        }
    }

    fun update(alarmItem: AlarmItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(alarmItem)
        }
    }

}