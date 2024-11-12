package com.atvantiq.parqngomall.ui.viewmodels.vehicles

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class VehicleVM(application: Application) : AndroidViewModel(application) {

    /*Variables declarations*/
    var clickEvents = MutableLiveData<VehiclesClickEvents>()
    var errorHandler = MutableLiveData<VehiclesErrorHandler>()

    var registrationNumber = ObservableField<String>().apply {
        set("")
    }
    var vehicleName = ObservableField<String>().apply {
        set("")
    }
    var vehicleType = ObservableField<String>().apply {
        set("")
    }

    /*Methods*/
    fun onAddVehicleClick(){
        clickEvents.value = VehiclesClickEvents.ON_ADD_VEHICLE_CLICK
    }

    fun onVehicleTypeClick(){
        clickEvents.value = VehiclesClickEvents.ON_VEHICLE_TYPE_CLICK
    }

    /*Validation Methods*/

    private fun isValidVehicleDetails():Boolean{
        return when{
            registrationNumber.get().toString().trim().isNullOrEmpty() -> {
                errorHandler.value = VehiclesErrorHandler.EMPTY_REGISTRATION_NUMBER
                false
            }

            vehicleName.get().toString().trim().isNullOrEmpty() -> {
                errorHandler.value = VehiclesErrorHandler.EMPTY_VEHICLE_NAME
                false
            }

            vehicleType.get().toString().trim().isNullOrEmpty() -> {
                errorHandler.value = VehiclesErrorHandler.EMPTY_VEHICLE_TYPE
                false
            }
            else ->{
                true
            }
        }
    }

    /*API Methods*/
    fun addVehicleAPI(){
        if(isValidVehicleDetails()){

        }
    }
}