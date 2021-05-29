package com.soham.todo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_edit_todo.*
import java.text.SimpleDateFormat
import java.util.*

const val DB_NAME = "todo.db"

class editTodo : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            DB_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)

        dateSelector.setOnClickListener {
            val tmpCalender = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                tmpCalender.set(Calendar.YEAR, year)
                tmpCalender.set(Calendar.MONTH, month)
                tmpCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate(tmpCalender)
            }

            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                tmpCalender.get(Calendar.YEAR),
                tmpCalender.get(Calendar.MONTH),
                tmpCalender.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }

    private fun updateDate(tmpCalender: Calendar) {
        //Mon, 5 Jan 2020
        val format = "EEE, d MM yyyy"
        val sdf = SimpleDateFormat(format)
        dateBox.setText(sdf.format(tmpCalender.time))

        timeBox.visibility = View.VISIBLE
        timeSelector.visibility = View.VISIBLE
    }
}