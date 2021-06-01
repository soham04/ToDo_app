package com.soham.todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_edit_todo.*
import java.text.SimpleDateFormat
import java.util.*


const val DB_NAME = "todo.db"

class editTodo : AppCompatActivity(), View.OnClickListener {

    private val db by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            DB_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    lateinit var tmpCalender: Calendar

    private val categoryList = arrayListOf<String>("Office", "Insurance", "Bussiness", "Travel", "Family", "Shopping")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)

        val selectedDate = dateSelector.setOnClickListener(this)
        timeSelector.setOnClickListener(this)
        setupSpinner()

        saveButton.setOnClickListener {
            Toast.makeText(this, tmpCalender.time.toString(), Toast.LENGTH_LONG).show()

            db.DAO1().insertTodo(
                Todo(
                    titleBox.text.toString(),
                    explainBox.text.toString(), spinner.selectedItem.toString(), tmpCalender, 1, titleBox.text.hashCode().toLong()
                )
            )

        }
    }

    private fun setupSpinner() {
        val aa: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1,
            categoryList
        )
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa
    }

    override fun onClick(v: View) {
        when (v.id) {
            dateSelector.id -> makeDateSelector()
            timeSelector.id -> makeTimeSelector()
            spinner.id -> setupSpinner()
        }
    }

    private fun makeDateSelector() {
        tmpCalender = Calendar.getInstance()
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

    private fun makeTimeSelector() {
        tmpCalender = Calendar.getInstance()
        val dateSetListener = TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hourOfDay: Int, minute: Int ->
            tmpCalender.set(Calendar.HOUR_OF_DAY, hourOfDay)
            tmpCalender.set(Calendar.MINUTE, minute)
            updateDate(tmpCalender)
        }

        val timePickerDialog = TimePickerDialog(
            this,
            dateSetListener,
            tmpCalender.get(Calendar.HOUR_OF_DAY),
            tmpCalender.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
        updateTime(tmpCalender)
    }

    private fun updateTime(tmpCalender: Calendar) {
        val format = "h:mm a"
        val sdf = SimpleDateFormat(format)
        timeBox.setText(sdf.format(tmpCalender.time))
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