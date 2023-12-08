package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityBrainTrainerAppsBinding

class BrainTrainerAppsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrainTrainerAppsBinding
    private var number1 = 50
    private var number2 = 50
    private var timer: CountDownTimer? = null
    private var correctAnswers = 0
    private var remainingTime: Long = 50000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrainTrainerAppsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGame()
        action()
    }

    private fun initGame() {
        updateNumbers()
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.timerText.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                showAlertDialog("Waktu habis", "Apakah kamu mau memulainya kembali ?", true)
            }
        }.start()
    }

    private fun action() {
        binding.startButton.setOnClickListener {
            val result = number1 + number2
            val resultEditText = binding.etResult.text.toString()

            if (result == resultEditText.toIntOrNull()) {
                correctAnswers++
                number1 *= 5
                number2 *= 3
                updateNumbers()
                updateScore()
            } else {
                showAlertDialog("Jawaban salah", "Coba Lagi!", false)
            }
        }

        binding.reset.setOnClickListener {
            showResetConfirmationDialog()
        }
    }

    private fun showResetConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Reset Game")
        builder.setMessage("Apakah kamu yakin ingin mereset permainan?")
        builder.setPositiveButton("Ya") { dialog, _ ->
            dialog.dismiss()
            restartGame()
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun updateScore() {
        binding.scoreText.text = "Score: $correctAnswers"
    }

    private fun updateNumbers() {
        val mathText = "$number1 + $number2"
        binding.math.text = mathText
        binding.etResult.text.clear()
    }

    private fun showAlertDialog(title: String, message: String, restartGame: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ya") { dialog, _ ->
            dialog.dismiss()
            if (restartGame) {
                restartGame()
            }
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
            if (!restartGame) {
                finish()
            }
        }
        builder.show()
    }

    private fun restartGame() {
        correctAnswers = 0
        number1 = 50
        number2 = 50
        updateNumbers()
        updateScore()
        // Batalkan timer sebelumnya
        timer?.cancel()
        // Mulai timer baru dengan sisa waktu sebelum direset
        startTimer(remainingTime)
    }

    private fun startTimer(initialMillis: Long) {
        timer = object : CountDownTimer(initialMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished
                val secondsRemaining = millisUntilFinished / 1000
                binding.timerText.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                showAlertDialog("Waktu habis", "Apakah kamu mau memulainya kembali?", true)
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
