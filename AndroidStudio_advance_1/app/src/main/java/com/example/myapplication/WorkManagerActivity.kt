package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.Operation
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.myapplication.databinding.ActivityWorkManagerBinding
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkManagerBinding
    private var foregroundWorkRequest: WorkRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartwork.setOnClickListener ( View.OnClickListener {
            Toast.makeText(this, "Memulai foreground work!", Toast.LENGTH_SHORT).show()

            foregroundWorkRequest = OneTimeWorkRequest.Builder(MyForegroundWork::class.java)
                .addTag("foregroundwork" + System.currentTimeMillis())
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    WorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.SECONDS
                )
                .build()
            WorkManager.getInstance(this).enqueue(foregroundWorkRequest!!)
        })

        binding.btnStopwork.setOnClickListener {
            MyForegroundWork.isStopped = true

            val operation: Operation = WorkManager.getInstance(this)
                .cancelWorkById(
                    foregroundWorkRequest!!.id
                )

            Toast.makeText(this, "Menghentikan foreground work!" + operation.state, Toast.LENGTH_SHORT).show()
        }

    }

}