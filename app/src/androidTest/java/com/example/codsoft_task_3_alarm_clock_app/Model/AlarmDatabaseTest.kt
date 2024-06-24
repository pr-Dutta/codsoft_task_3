package com.example.codsoft_task_3_alarm_clock_app.Model

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlarmDatabaseTest : TestCase() {

    private lateinit var alarmDao: AlarmDao
    private lateinit var db: AlarmDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        var context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AlarmDatabase::class.java).build()
        alarmDao = db.alarmDao()
    }

    @After
    public override fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertAndGetAll() = runBlocking {
        val alarm = AlarmItem(1, 10, 10)
        alarmDao.insert(alarm)
        var alarms = alarmDao.getAll().getOrAwaitValue()
        assertThat(alarms, hasItem(alarm))
    }

    @Test
    fun testDeleteAndGetAll() = runBlocking {
        val alarm1 = AlarmItem(1, 10, 10)
        val alarm2 = AlarmItem(2, 20, 20)

        alarmDao.insert(alarm1)
        alarmDao.insert(alarm2)
        alarmDao.delete(alarm1)
        var alarms = alarmDao.getAll().getOrAwaitValue()
        assertThat(alarms, not(hasItem(alarm1)))
    }

    @Test
    fun testUpdateAndGetAll() = runBlocking {
        val alarm1 = AlarmItem(1, 10, 10)
        val test = AlarmItem(2, 20, 20)
        alarmDao.insert(alarm1)
        alarm1.hour = 20
        alarm1.minute = 20
        alarmDao.update(alarm1)
        var alarms = alarmDao.getAll().getOrAwaitValue()
        assertThat(alarms, hasItem(test))
    }
}









