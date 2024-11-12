package com.atvantiq.parqngomall.ui.dialogs

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingBottomSheetFragment
import com.atvantiq.parqngomall.data.model.VehicleType
import com.atvantiq.parqngomall.databinding.DialogVehicleTypeBinding
import com.atvantiq.parqngomall.ui.adapters.VehicleTypeAdapter


class VehicleTypeDialog(var onVehicleTypeSelected:(type: VehicleType)->Unit) : BaseBindingBottomSheetFragment<DialogVehicleTypeBinding>() {

    private lateinit var vehicleTypes: List<VehicleType>

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.dialog_vehicle_type)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        vehicleTypes = listOf(
            VehicleType("Car", "Car"),
            VehicleType("Motorcycle", "Motorcycle"),
            VehicleType("Truck", "Truck"),
            VehicleType("Bus", "Bus")
        )

        binding.recyclerViewVehicleType.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewVehicleType.adapter = VehicleTypeAdapter(vehicleTypes){
            onVehicleTypeSelected(it)
            dismiss()
        }
    }
}