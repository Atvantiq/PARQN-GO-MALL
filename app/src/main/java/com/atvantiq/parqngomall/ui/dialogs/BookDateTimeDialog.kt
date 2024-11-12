package com.atvantiq.parqngomall.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingBottomSheetFragment
import com.atvantiq.parqngomall.data.model.VehicleDetailItem
import com.atvantiq.parqngomall.databinding.DialogBookDateTimeBinding
import com.atvantiq.parqngomall.databinding.DialogProfileCompleteBinding
import com.atvantiq.parqngomall.ui.features.profile.ProfileCompleteActivity
import com.atvantiq.parqngomall.utils.DateUtils
import com.atvantiq.parqngomall.utils.Utils
import java.util.Date

class BookDateTimeDialog(var onDateTimeSelected:(selectedDate:String,selectedTime:String)->Unit) : BaseBindingBottomSheetFragment<DialogBookDateTimeBinding>() {

	private var selectedDate:String =""
	private var selectedTime:String =""
	private var selectedVehicle: VehicleDetailItem? = null
	private var parkingCharges = 100
	private var parkingHours = 1

	override val fragmentBinding: FragmentBinding
		get() = FragmentBinding(R.layout.dialog_book_date_time)

	override fun onCreateViewFragment(savedInstanceState: Bundle?) {

	}

	override fun onViewStateRestored(savedInstanceState: Bundle?) {
		super.onViewStateRestored(savedInstanceState)
		binding.parkingCharges = parkingCharges
		updateSelectedDurationText(binding.sliderDuration.value.toInt())
		setListeners()
	}

	private fun setListeners(){
		binding.dateEt.setOnClickListener {
			binding.dateEt.error = null
			DateUtils.onDateClickWithLimit(requireContext(), object : DateUtils.DateCallBack{
				override fun onDateSelected(date: String, formatDate: String) {
					selectedDate = date
					binding.date = date
				}
			},true)
		}

		binding.timeEt.setOnClickListener {
			if(selectedDate.isEmpty() || selectedDate == "" || selectedDate.isBlank()){
				binding.dateEt.error ="Select date first"
			}else{
				binding.timeEt.error = null
				DateUtils.showTimerPicker(requireContext(), object :DateUtils.TimeCallBack{
					override fun onTimeSelected(time: String, formatTime: String) {
						selectedTime = time
						binding.time = time
					}
				})
			}
		}

		binding.bookButton.setOnClickListener {
			if(isValidDetails()){
				onDateTimeSelected.invoke(selectedDate,selectedTime)
				dismiss()
			}
		}

		binding.sliderDuration.addOnChangeListener { _, value, _ ->
			updateSelectedDurationText(value.toInt())
			parkingHours = value.toInt()
			calculateParkingCharges()
		}

		binding.vehicleEt.setOnClickListener {
			binding.vehicleEt.error = null
			var vehicleDialog = SelectVehicleDialog{
				selectedVehicle = it
				binding.vehicleEt.setText(it.name)
			}
			vehicleDialog.show(childFragmentManager,"SelectVehicle")
		}

		binding.regularBt.setOnClickListener{
			parkingCharges = 100
			calculateParkingCharges()
		}

		binding.evChargingBt.setOnClickListener {
			parkingCharges = 200
			calculateParkingCharges()
		}
	}

	private fun calculateParkingCharges(){
		binding.parkingCharges = parkingCharges * parkingHours
	}

	private fun updateSelectedDurationText(duration: Int) {
		val durationText = "$duration hour${if (duration > 1) "s" else ""}"
		binding.durationSliderValue = durationText.toString()
	}

	private fun isValidDetails(): Boolean{
		return  when{
			selectedDate.isEmpty() || selectedDate == "" || selectedDate.isBlank() ->{
				binding.dateEt.error = "Select date"
				return false
			}
			selectedTime.isEmpty() || selectedTime == "" || selectedTime.isBlank() ->{
				binding.timeEt.error = "Select time"
				return false
			}
			selectedVehicle == null -> {
				binding.vehicleEt.error = "Select Vehicle"
				return false
			}
			else->{
				true
			}
		}
	}
}