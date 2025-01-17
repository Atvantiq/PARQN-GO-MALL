package com.atvantiq.parqngomall.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.TimePicker
import android.widget.Toast
import com.atvantiq.parqngomall.R
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
	
	fun onDateClick(context: Context, callBack: DateCallBack) {
		val c = Calendar.getInstance()
		val mYear = c.get(Calendar.YEAR)
		val mMonth = c.get(Calendar.MONTH)
		val mDay = c.get(Calendar.DAY_OF_MONTH)
		val datePickerDialog = DatePickerDialog(
			context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
				val calendar = Calendar.getInstance()
				calendar.set(year, monthOfYear, dayOfMonth)
				val format = SimpleDateFormat("yyyy-MM-dd")
				//val formatapi = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				val formatapi = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				val strDate = format.format(calendar.time)
				val formatDate = formatapi.format(calendar.time)
				callBack.onDateSelected(strDate, formatDate)
			}, mYear, mMonth, mDay
		)
		datePickerDialog.show()
	}
	
	fun onDOBPickerClick(context: Context, callBack: DateCallBack) {
		var format = SimpleDateFormat("dd/MM/yyyy")
		var formatapi = SimpleDateFormat("dd/MM/yyyy")
		val calendar = Calendar.getInstance()
		calendar.set(1990,1,1)
		val mYear = calendar.get(Calendar.YEAR)
		val mMonth = calendar.get(Calendar.MONTH)
		val mDay = calendar.get(Calendar.DAY_OF_MONTH)
		val datePickerDialog = DatePickerDialog(
			context, R.style.AppTheme_DatePickerDialog,
			DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
				val calendar = Calendar.getInstance()
				calendar.set(year, monthOfYear, dayOfMonth)
				val strDate = format.format(calendar.time)
				val formatDate = formatapi.format(calendar.time)
				callBack.onDateSelected(strDate, formatDate)
			},
			mYear,
			mMonth,
			mDay
		)
		datePickerDialog.datePicker.maxDate = Date().time
		datePickerDialog.show()
	}
	
	fun onDateClickWithLimit(
		context: Context,
		callBack: DateCallBack,
		isAfterCurrentDate: Boolean
	) {
		var format = SimpleDateFormat("yyyy-MM-dd")
		var formatapi = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
		
		val calendar = Calendar.getInstance()
		val mYear = calendar.get(Calendar.YEAR)
		val mMonth = calendar.get(Calendar.MONTH)
		val mDay = calendar.get(Calendar.DAY_OF_MONTH)
		val datePickerDialog = DatePickerDialog(
			context, R.style.AppTheme_DatePickerDialog,
			DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
				val calendar = Calendar.getInstance()
				calendar.set(year, monthOfYear, dayOfMonth)
				val strDate = format.format(calendar.time)
				val formatDate = formatapi.format(calendar.time)
				callBack.onDateSelected(strDate, formatDate)
			},
			mYear,
			mMonth,
			mDay
		)
		if (isAfterCurrentDate) {
			datePickerDialog.datePicker.minDate = calendar.timeInMillis
		} else {
			datePickerDialog.datePicker.maxDate = calendar.timeInMillis
		}
		datePickerDialog.show()
	}

	fun showTimerPicker(context: Context,callBack: TimeCallBack){
		val calendar: Calendar = Calendar.getInstance()
		val hour = calendar.get(Calendar.HOUR_OF_DAY)
		val minute = calendar.get(Calendar.MINUTE)
		val timePickerDialog = TimePickerDialog(context,R.style.AppTheme_DatePickerDialog,
			{ _: TimePicker, selectedHour: Int, selectedMinute: Int ->
				val format = if (selectedHour >= 12) "PM" else "AM"
				val hourIn12Format = if (selectedHour == 0) 12 else if (selectedHour > 12) selectedHour - 12 else selectedHour
				var time = "$hourIn12Format:$selectedMinute $format"
				var formatTime = "$hourIn12Format:$selectedMinute"
				callBack.onTimeSelected(time,formatTime)
				//Toast.makeText(context, "Selected Time: $hourIn12Format:${String.format("%02d", selectedMinute)} $format", Toast.LENGTH_SHORT).show()
			}, hour, minute, false // false means 12-hour format
		)
		timePickerDialog.show()
	}
	
	@SuppressLint("SimpleDateFormat")
	fun getCurrentDate(): String {
		val c = Calendar.getInstance().time
		println("Current time => $c")
		val df = SimpleDateFormat("yyyy-MM-dd")
		return df.format(c)
	}
	
	@SuppressLint("SimpleDateFormat")
	fun formatDate(date: String): String {
		if (date.isNullOrEmpty()) {
			return ""
		}
		val df = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
		try {
			val c = df.parse(date)
			val df1 = SimpleDateFormat("dd/MM/yyyy")
			return df1.format(c)
		} catch (e: Exception) {
			return ""
		}
	}
	

	fun convertFrom24(inTime:String):String{
		try {
			val timeFormat24 = SimpleDateFormat("HH:mm:ss")
			val timeFormat12 = SimpleDateFormat("hh:mm a")
			var inTimeParsed = timeFormat24.parse(inTime)
			return timeFormat12.format(inTimeParsed)
		}catch (e: Exception){
			return  ""
		}
	}
	
	
	interface DateCallBack {
		fun onDateSelected(date: String, formatDate: String)
	}

	interface TimeCallBack {
		fun onTimeSelected(time: String, formatTime: String)
	}
	
}