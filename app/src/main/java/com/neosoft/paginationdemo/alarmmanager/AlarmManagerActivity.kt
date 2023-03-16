package com.neosoft.paginationdemo.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.neosoft.paginationdemo.MyReceiver
import com.neosoft.paginationdemo.R
import com.neosoft.paginationdemo.databinding.ActivityAlarmManagerBinding
import java.util.*
import android.app.TimePickerDialog.OnTimeSetListener as OnTimeSetListener1


class AlarmManagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlarmManagerBinding

    var timePickerDialog: TimePickerDialog? = null
    val RQS_1 = 1
    lateinit var myReceiver: AlarmReceiver

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alarm_manager)

        binding.startAlaram.setOnClickListener {
            binding.alarmprompt.text = ""
            openTimePickerDialog(false)
        }

    }




    private fun openTimePickerDialog(is24Hrs: Boolean) {

        val calendar = Calendar.getInstance()

        timePickerDialog = TimePickerDialog(
            this@AlarmManagerActivity,
            onTimeSetListener, calendar[Calendar.HOUR_OF_DAY],
            calendar[Calendar.MINUTE], is24Hrs
        )
        timePickerDialog!!.setTitle("Set Alarm Time")

        timePickerDialog!!.show()

    }

    var onTimeSetListener =
        OnTimeSetListener1 { view, hourOfDay, minute ->
            val calNow = Calendar.getInstance()
            val calSet = calNow.clone() as Calendar
            calSet[Calendar.HOUR_OF_DAY] = hourOfDay
            calSet[Calendar.MINUTE] = minute
            calSet[Calendar.SECOND] = 0
            calSet[Calendar.MILLISECOND] = 0
            if (calSet.compareTo(calNow) <= 0) {
                // Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1)
            }
            setAlarm(calSet)
        }


    private fun setAlarm(targetCal: Calendar) {

        binding.alarmprompt.setText(
            """***
                Alarm is set ${targetCal.time}
                """.trimIndent() + "\n" + "***\n"
        )

        val intent = Intent(baseContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            baseContext, RQS_1, intent, 0
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.RTC_WAKEUP, targetCal.timeInMillis] = pendingIntent
    }



}