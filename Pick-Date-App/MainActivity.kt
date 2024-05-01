package com.example.pickadate
/*
    The app displays a date the user selects in DatePickerDialog
    @author: Kate Chavira
    @version: 02/05/2024
 */

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private val max = 3
    private var selectedDates = mutableListOf<Calendar>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pickDateButton: Button = findViewById(R.id.pickDate)
        val showButton: Button = findViewById(R.id.show)

        pickDateButton.setOnClickListener {
            if (counter < max) {
            showDatePickerDialog()
            } else {
            Toast.makeText(this, "You have exceeded the number of clicks allowed to pick a date", Toast.LENGTH_SHORT).show()
            }
           }

        showButton.setOnClickListener {
            if (selectedDates.size == max) {
            showSelectedDates()
            } else {
            Toast.makeText(this, "Please pick three dates first", Toast.LENGTH_SHORT).show()
              }
          }
        }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
        this,
        { _, s_year, s_month, s_day ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.YEAR, s_year)
            selectedDate.set(Calendar.MONTH, s_month)
            selectedDate.set(Calendar.DAY_OF_MONTH, s_day)
            selectedDates.add(selectedDate)
            counter++
            val remainingPicks = max - counter
            Toast.makeText(this, "Picks remaining: $remainingPicks", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        )
        datePicker.show()
        }

     private fun showExceededLimitDialog() {
       AlertDialog.Builder(this)
        .setMessage("You have exceeded the number of date picks allowed")
        .setPositiveButton("OK", null)
        .show()
        }

    private fun showSelectedDates() {
        val activity1: TextView = findViewById(R.id.activity1)
        val activity2: TextView = findViewById(R.id.activity2)
        val activity3: TextView = findViewById(R.id.activity3)

        activity1.text = "Activity 1 on ${formatDate(selectedDates[0])}"
        activity2.text = "Activity 2 on ${formatDate(selectedDates[1])}"
        activity3.text = "Activity 3 on ${formatDate(selectedDates[2])}"
        }

    private fun formatDate(calendar: Calendar): String {
        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        val year = calendar.get(Calendar.YEAR)
        val month = String.format("%02d", calendar.get(Calendar.MONTH) + 1) // Adding 1 because Calendar.MONTH is zero-based
        val dayOfMonth = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))
        return "$dayOfWeek, $year/$month/$dayOfMonth"
    }
}