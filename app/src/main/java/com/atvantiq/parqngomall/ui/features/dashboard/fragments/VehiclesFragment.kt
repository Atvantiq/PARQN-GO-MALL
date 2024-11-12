package com.atvantiq.parqngomall.ui.features.dashboard.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseFragment
import com.atvantiq.parqngomall.databinding.FragmentVehiclesBinding
import com.atvantiq.parqngomall.ui.adapters.VehicleDetailAdapter
import com.atvantiq.parqngomall.ui.features.addVehicles.AddVehicleActivity
import com.atvantiq.parqngomall.ui.viewmodels.vehicles.VehicleVM
import com.atvantiq.parqngomall.ui.viewmodels.vehicles.VehiclesClickEvents
import com.atvantiq.parqngomall.utils.Utils
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class VehiclesFragment : BaseFragment<FragmentVehiclesBinding, VehicleVM>() {

    private lateinit var vehicleDetailAdapter: VehicleDetailAdapter

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.fragment_vehicles,VehicleVM::class.java)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        vehicleDetails()
        Checkout.preload(requireContext())
    }

    private fun vehicleDetails(){
        vehicleDetailAdapter = VehicleDetailAdapter(MoreOptionConstants.getVehicleList())
        binding.recyclerViewVehicles.hasFixedSize()
        binding.recyclerViewVehicles.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewVehicles.adapter = vehicleDetailAdapter
    }

    override fun subscribeToEvents(vm: VehicleVM) {
        binding.vm = vm

        vm.clickEvents.observe(viewLifecycleOwner, Observer {
            when(it){
                VehiclesClickEvents.ON_ADD_VEHICLE_CLICK -> {
                   Utils.jumpActivity(requireContext(), AddVehicleActivity::class.java)
                }
                VehiclesClickEvents.ON_VEHICLE_TYPE_CLICK -> TODO()
            }
        })
    }
}