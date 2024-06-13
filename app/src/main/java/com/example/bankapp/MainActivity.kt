package com.example.bankapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<RegisterBankDetailsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelObserver()

        val textWatcher = SimpleTextWatcher {
            val pan = binding.editPanNumber.text.toString()
            val day = binding.editDay.text.toString()
            val month = binding.editMonth.text.toString()
            val year = binding.editYear.text.toString()
            viewModel.bankDetailsValidation(pan, day, month, year)
        }

        binding.editPanNumber.addTextChangedListener(textWatcher)
        binding.editDay.addTextChangedListener(textWatcher)
        binding.editMonth.addTextChangedListener(textWatcher)
        binding.editYear.addTextChangedListener(textWatcher)

        binding.btnNext.setOnClickListener {
            Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.txtDontHavePanNumber.setOnClickListener {
            finish()
        }
    }

    private fun viewModelObserver() {
        viewModel.bankDetailsLiveData.observe(this) { isValid ->
            binding.btnNext.isEnabled = isValid
        }
    }
}