package com.atvantiq.parqngomall.ui.features.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseFragment
import com.atvantiq.parqngomall.databinding.FragmentHistoryBinding
import com.atvantiq.parqngomall.ui.adapters.ParkingHistoryAdapter
import com.atvantiq.parqngomall.ui.viewmodels.dashboard.DashboardVM
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants


class HistoryFragment : BaseFragment<FragmentHistoryBinding,DashboardVM>() {

    private lateinit var parkingHistoryAdapter : ParkingHistoryAdapter

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.fragment_history,DashboardVM::class.java)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        parkingHistory()
    }

    private fun parkingHistory(){
        parkingHistoryAdapter = ParkingHistoryAdapter(MoreOptionConstants.getParkingHistory())
        binding.vehicleHistoryList.hasFixedSize()
        binding.vehicleHistoryList.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.vehicleHistoryList.adapter = parkingHistoryAdapter
    }

    override fun subscribeToEvents(vm: DashboardVM) {

    }

}