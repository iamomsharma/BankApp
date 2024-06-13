package com.example.bankapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar

class RegisterBankDetailsViewModel : ViewModel(){

    private val _bankDetailsValidation = MutableLiveData<Boolean>()
    val bankDetailsLiveData: LiveData<Boolean> get() = _bankDetailsValidation

    fun bankDetailsValidation(pan: String, day: String, month: String, year: String) {
        val isPanValid = isValidPAN(pan)
        val isBirthdateValid = isValidDate(day, month, year)
        _bankDetailsValidation.value = isPanValid && isBirthdateValid
    }

    private fun isValidPAN(pan: String): Boolean {
        val pattern = Regex("[A-Z]{5}[0-9]{4}[A-Z]{1}")
        return pattern.matches(pan)
    }

    private fun isValidDate(day: String, month: String, year: String): Boolean {
        return try {
            val dayInt = day.toInt()
            val monthInt = month.toInt()
            val yearInt = year.toInt()
            val calendar = Calendar.getInstance()
            calendar.setLenient(false)
            calendar.set(yearInt, monthInt - 1, dayInt) // Month is 0-based in Calendar
            calendar.time
            true
        } catch (e: Exception) {
            false
        }
    }
}