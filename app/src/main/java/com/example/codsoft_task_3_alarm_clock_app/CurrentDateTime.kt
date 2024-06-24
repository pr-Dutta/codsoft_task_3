package com.example.codsoft_task_3_alarm_clock_app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CurrentDateTime() {

    val timeFormat = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    var currentTime by remember { mutableStateOf(timeFormat.format(Calendar.getInstance().time)) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime =  timeFormat.format(Calendar.getInstance().time)
            delay(1000)
        }
    }

    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    var currentDate by remember { mutableStateOf(dateFormat.format(Calendar.getInstance().time)) }

    LaunchedEffect(Unit) {
        while (true) {
            currentDate = dateFormat.format(Calendar.getInstance().time)

            val now = Calendar.getInstance()
            val nextMidnight = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, 1)
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val delayUntilNextMidnight = nextMidnight.timeInMillis - now.timeInMillis

            delay(delayUntilNextMidnight)
        }
    }

    Text(
        text = currentTime,
        fontSize = 32.sp,
        modifier = Modifier.padding(8.dp)
    )

    Text(
        text = currentDate,
        fontSize = 24.sp,
        modifier = Modifier.padding(8.dp)
    )
}