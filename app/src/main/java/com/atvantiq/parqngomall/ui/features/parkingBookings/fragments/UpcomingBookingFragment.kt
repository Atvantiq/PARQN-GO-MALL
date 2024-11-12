package com.atvantiq.parqngomall.ui.features.parkingBookings.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingActivity
import com.atvantiq.parqngomall.base.BaseBindingFragment
import com.atvantiq.parqngomall.databinding.FragmentUpcomingBookingBinding
import com.atvantiq.parqngomall.ui.adapters.BookingsPagerAdapter
import com.atvantiq.parqngomall.ui.adapters.ParkingBookingAdapter
import com.atvantiq.parqngomall.ui.adapters.ParkingHistoryAdapter
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants

class UpcomingBookingFragment : BaseBindingFragment<FragmentUpcomingBookingBinding>() {

    private lateinit var bookingAdapter:ParkingBookingAdapter

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.fragment_upcoming_booking)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        parkingHistory()
    }

    private fun parkingHistory(){
        bookingAdapter = ParkingBookingAdapter(isPastBooking = false)
        binding?.upcomingBookingsList?.hasFixedSize()
        binding?.upcomingBookingsList?.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding?.upcomingBookingsList?.adapter = bookingAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpcomingBookingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}