package com.example.codsoft_task_3_alarm_clock_app.Model

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.BroadcastOptions
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.getSystemService
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codsoft_task_3_alarm_clock_app.BroadCastReceiver.AlarmBroadCastReceiver
import com.example.codsoft_task_3_alarm_clock_app.Service.AlarmService
import java.time.LocalDateTime
import java.util.Calendar

@Entity("alarm")
data class AlarmItem(
    @PrimaryKey(autoGenerate = true) val uid: Long?,
    @ColumnInfo var hour: Int,
    @ColumnInfo var minute: Int,
    @ColumnInfo var mon: Boolean,
    @ColumnInfo var tue: Boolean,
    @ColumnInfo var wed: Boolean,
    @ColumnInfo var thu: Boolean,
    @ColumnInfo var fri: Boolean,
    @ColumnInfo var sat: Boolean,
    @ColumnInfo var sun: Boolean,
    @ColumnInfo var start: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    constructor(id: Long, hour: Int, minute: Int) : this (
        id,
        hour,
        minute,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false
    )

    fun getTime() : String {
        return "$hour:$minute"
    }

    fun getRepeat() : String {
        var builder = StringBuilder()
        if (mon) {
            builder.append("Mon")
        }
        if (tue) {
            builder.append(", Tue")
        }
        if (wed) {
            builder.append(", Wed")
        }
        if (thu) {
            builder.append(", Thu")
        }
        if (fri) {
            builder.append(", Fri")
        }
        if (sat) {
            builder.append(", Sat")
        }
        if (sun) {
            builder.append(", Sun")
        }
        return builder.toString()
    }

    @SuppressLint("ScheduleExactAlarm")
    fun schedule(context: Context) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadCastReceiver::class.java)
        intent.putExtra(AlarmBroadCastReceiver.MONDAY, mon)
        intent.putExtra(AlarmBroadCastReceiver.TUESDAY, tue)
        intent.putExtra(AlarmBroadCastReceiver.WEDNESDAY, wed)
        intent.putExtra(AlarmBroadCastReceiver.THURSDAY, thu)
        intent.putExtra(AlarmBroadCastReceiver.FRIDAY, fri)
        intent.putExtra(AlarmBroadCastReceiver.SATURDAY, sat)
        intent.putExtra(AlarmBroadCastReceiver.SUNDAY, sun)
        intent.putExtra(AlarmBroadCastReceiver.RECURRING, isLoop())

        val pendingIntent = PendingIntent.getBroadcast(context, uid?.toInt()!!, intent, PendingIntent.FLAG_IMMUTABLE)

        val calender = Calendar.getInstance()
        calender.timeInMillis = System.currentTimeMillis()
        calender.set(Calendar.HOUR_OF_DAY, hour)
        calender.set(Calendar.MINUTE, minute)
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.MILLISECOND, 0)

        if (calender.timeInMillis <= System.currentTimeMillis()) {
            calender.set(Calendar.DAY_OF_WEEK, calender.get(Calendar.DAY_OF_WEEK) + 1)
        }

        val oneDay: Long = 24 * 60 * 60 * 1000

        if (!isLoop()) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calender.timeInMillis, pendingIntent)
        }else {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calender.timeInMillis, oneDay, pendingIntent)
        }

        start = true
    }

    fun cancel(context: Context) {
        val alarmManager : AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadCastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, uid?.toInt()!!, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)
        start = false
    }

    private fun isLoop(): Boolean {
        return mon || tue || wed || thu || fri || sat || sun
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(uid)
        parcel.writeInt(hour)
        parcel.writeInt(minute)
        parcel.writeByte(if (mon) 1 else 0)
        parcel.writeByte(if (tue) 1 else 0)
        parcel.writeByte(if (wed) 1 else 0)
        parcel.writeByte(if (thu) 1 else 0)
        parcel.writeByte(if (fri) 1 else 0)
        parcel.writeByte(if (sat) 1 else 0)
        parcel.writeByte(if (sun) 1 else 0)
        parcel.writeByte(if (start) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlarmItem> {
        override fun createFromParcel(parcel: Parcel): AlarmItem {
            return AlarmItem(parcel)
        }

        override fun newArray(size: Int): Array<AlarmItem?> {
            return arrayOfNulls(size)
        }
    }
}










