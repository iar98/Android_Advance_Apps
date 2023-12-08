package com.example.myapplication


import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.databinding.ActivityAlaramManagerBinding

class AlaramManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlaramManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlaramManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
    }

    fun clickSetAlarm(view: View) {
        binding.tvAlarmPrompt.text = ""
        openTimePickerDialog()
    }

    private fun openTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            val calNow = Calendar.getInstance()

            val calSet = Calendar.getInstance()
            calSet.apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            when {
                calSet <= calNow -> {
                    // Today Set time passed, count to tomorrow
                    calSet.add(Calendar.DATE, 1)
                    Log.i("hasil", " =<0")
                }
                calSet > calNow -> {
                    Log.i("hasil", " > 0")
                }
                else -> {
                    Log.i("hasil", " else ")
                }
            }
            setAlarm(calSet)
        }
        val timePickerDialog = TimePickerDialog(this,timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),true)
        timePickerDialog.setTitle("Set Alarm Time")
        timePickerDialog.show()
    }

    private fun setAlarm(targetCal: Calendar) {
        binding.tvAlarmPrompt.text = """
                ***
                Alarm set on ${targetCal.time}
                ***
                """.trimIndent()
        val intent = Intent(this@AlaramManagerActivity, AlaramReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this@AlaramManagerActivity, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            targetCal.timeInMillis,
            pendingIntent
        )
    }
}