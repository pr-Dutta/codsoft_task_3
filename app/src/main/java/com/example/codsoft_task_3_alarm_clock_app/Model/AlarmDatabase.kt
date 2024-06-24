package com.example.codsoft_task_3_alarm_clock_app.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlarmItem::class], version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun alarmDao() : AlarmDao

    companion object {
        private var INSTANCE: AlarmDatabase? = null

        fun getInstance(context: Context) : AlarmDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, AlarmDatabase::class.java, context.packageName).build()
            }
            return INSTANCE as AlarmDatabase
        }
    }
}