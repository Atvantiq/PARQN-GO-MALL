package com.atvantiq.parqngomall.ui.features.addVehicles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityAddVehicleBinding
import com.atvantiq.parqngomall.ui.dialogs.VehicleTypeDialog
import com.atvantiq.parqngomall.ui.viewmodels.vehicles.VehicleVM
import com.atvantiq.parqngomall.ui.viewmodels.vehicles.VehiclesClickEvents
import com.atvantiq.parqngomall.ui.viewmodels.vehicles.VehiclesErrorHandler


class AddVehicleActivity : BaseActivity<ActivityAddVehicleBinding, VehicleVM>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_add_vehicle,VehicleVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        handleToolbar()
    }

    private  fun handleToolbar(){
        binding.addVehicleToolbar.toolbarTitle.text = getString(R.string.add_vehicle)
        binding.addVehicleToolbar.toolbarBackBt.setOnClickListener {
            finish()
        }
    }

    override fun subscribeToEvents(vm: VehicleVM) {
        binding.vm = vm

        vm.errorHandler.observe(this, Observer {
            when(it){
                VehiclesErrorHandler.EMPTY_REGISTRATION_NUMBER -> {
                    binding?.registrationNumberEt?.error = getString(R.string.enter_registration_number)
                    binding?.registrationNumberEt?.requestFocus()
                    shakeEditText(this,binding.registrationNumberEt)
                }
                VehiclesErrorHandler.EMPTY_VEHICLE_NAME -> {
                    binding?.nameEt?.error = getString(R.string.enter_vehicle_name)
                    binding?.nameEt?.requestFocus()
                    shakeEditText(this,binding.nameEt)

                }
                VehiclesErrorHandler.EMPTY_VEHICLE_TYPE -> {
                    binding?.vehicleType?.error = getString(R.string.select_vehicle_type)
                    binding?.vehicleType?.requestFocus()
                    shakeEditText(this,binding.vehicleType)
                }
            }
        })

        vm.clickEvents.observe(this, Observer {
            when(it){
                VehiclesClickEvents.ON_ADD_VEHICLE_CLICK ->{
                    //
                }
                VehiclesClickEvents.ON_VEHICLE_TYPE_CLICK -> {
                    VehicleTypeDialog{
                        binding?.vehicleType?.error = null
                        vm.vehicleType.set(it.name)
                    }.show(supportFragmentManager, "VehicleTypeBottomSheet")
                }
            }
        })
    }

}