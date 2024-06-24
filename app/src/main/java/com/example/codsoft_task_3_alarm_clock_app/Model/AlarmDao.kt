package com.example.codsoft_task_3_alarm_clock_app.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlarmDao {

    @Insert
    suspend fun insert(alarmItem: AlarmItem)

    @Update
    suspend fun update(alarmItem: AlarmItem)

    @Delete
    suspend fun delete(alarmItem: AlarmItem)

    @Query("SELECT * FROM alarm")
    fun getAll() : LiveData<List<AlarmItem>>
}