package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? =null
    private var tvAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndatepicker : Button = findViewById(R.id.btndatepicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btndatepicker.setOnClickListener {
            clickdatepicker()
        }
    }

    private fun clickdatepicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _ , selectedyear, selectedmonth, selecteddayofMonth ->

                Toast.makeText(this,
                    "Year was $selectedyear, month was ${selectedmonth+1}, dayofmonth was $selecteddayofMonth " , Toast.LENGTH_LONG).show()

                val selectedDate = "$selecteddayofMonth/${selectedmonth+1}/$selectedyear"

                tvSelectedDate?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val differenceInMinutes = currentDateInMinutes-selectedDateInMinutes

                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }

                }


            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()



    }
}

