package com.example.alarmmanagerguide

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.alarmmanagerguide.models.Person
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import java.time.LocalDateTime
class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setShowWhenLocked(true)
        setTurnScreenOn(true)
//        val vibrator = getSystemService(Vibrator::class.java)
//        val pattern = longArrayOf(0, 500, 500)
//        val effect = VibrationEffect.createWaveform(pattern, 0)
//        vibrator.vibrate(effect)
        setContent {
            Button(
                onClick = {
                    lifecycleScope.launch {
                        AlarmApplication.realm.write {
                            val record = query<Person>(
                                "name == $0",
                                "John"
                            )
                                .first()
                                .find()!!
                            record.name = "Nick"
                        }

                        val vibrator = getSystemService(Vibrator::class.java)
                        vibrator.cancel()

                        finish()
                    }
                },
                modifier = Modifier.padding(30.dp)
            ) {
            }
        }
    }
}
