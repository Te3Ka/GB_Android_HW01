package ru.te3ka.passangercounteronthebus

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.te3ka.passangercounteronthebus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var counter = 0
        var counterText = binding.counterPassengers

        counterText.text = counter.toString()
        binding.minus.isEnabled = false

        binding.minus.setOnClickListener {
            counter--
            counterText.text = counter.toString()
            if (counter == 0) {
                binding.minus.isEnabled = false
                binding.textDescription.setTextColor(Color.rgb(0, 153, 0))
                binding.textDescription.text = "Все места свободны"
            } else if (counter >= 50) {
                binding.resetCounter.visibility = View.VISIBLE
                binding.resetCounter.isEnabled = true
                binding.textDescription.setTextColor(Color.rgb(153, 0, 0))
                binding.textDescription.text = "Слишком много пассажиров!"
            } else {
                binding.minus.isEnabled = true
                binding.textDescription.setTextColor(Color.rgb(0, 0, 153))
                binding.textDescription.text = "Осталось свободных мест: " + (49 - counter)
            }
        }

        binding.plus.setOnClickListener {
            counter++;
            counterText.text = counter.toString()
            if (counter in 1..49) {
                binding.minus.isEnabled = true
                binding.textDescription.setTextColor(Color.rgb(0, 0, 153))
                binding.textDescription.text = "Осталось свободных мест: " + (49 - counter)
            } else if (counter >= 50) {
                binding.resetCounter.visibility = View.VISIBLE
                binding.resetCounter.isEnabled = true
                binding.textDescription.setTextColor(Color.rgb(153, 0, 0))
                binding.textDescription.text = "Слишком много пассажиров!"
            }
        }

        binding.resetCounter.setOnClickListener {
            binding.resetCounter.isEnabled = false
            binding.resetCounter.visibility = View.INVISIBLE
            counter = 0
            counterText.text = counter.toString()
            binding.minus.isEnabled = false
            binding.textDescription.setTextColor(Color.rgb(0, 153, 0))
            binding.textDescription.text = "Все места свободны"
        }
    }
}