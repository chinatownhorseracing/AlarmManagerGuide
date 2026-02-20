package com.example.alarmmanagerguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AlarmScheduler(this)
        setContent {
                val secondsText = 7L
                val message = "test message"
            Column() {
                Button(
                    onClick = {
                        val alarmItem = AlarmItem(
                            time = LocalDateTime.now()
                                .plusSeconds(secondsText),
                            message = message
                        )
                        scheduler.schedule(alarmItem)
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Schedule")
                }
                var name by remember { mutableStateOf("john") }
                Text(name)
            }
        }
    }
}
