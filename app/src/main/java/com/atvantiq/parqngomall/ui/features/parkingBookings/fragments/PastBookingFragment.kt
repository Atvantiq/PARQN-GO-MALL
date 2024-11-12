package com.atvantiq.parqngomall.ui.features.parkingBookings.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingFragment
import com.atvantiq.parqngomall.databinding.FragmentPastBookingBinding
import com.atvantiq.parqngomall.ui.adapters.ParkingBookingAdapter


class PastBookingFragment : BaseBindingFragment<FragmentPastBookingBinding>() {

    private lateinit var bookingAdapter: ParkingBookingAdapter

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.fragment_past_booking)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        parkingHistory()
    }

    private fun parkingHistory() {
        bookingAdapter = ParkingBookingAdapter(isPastBooking = true)
        binding?.pastBookingsList?.hasFixedSize()
        binding?.pastBookingsList?.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding?.pastBookingsList?.adapter = bookingAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PastBookingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}