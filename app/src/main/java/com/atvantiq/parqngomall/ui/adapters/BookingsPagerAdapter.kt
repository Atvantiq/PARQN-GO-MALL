package com.atvantiq.parqngomall.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.atvantiq.parqngomall.ui.features.parkingBookings.fragments.PastBookingFragment
import com.atvantiq.parqngomall.ui.features.parkingBookings.fragments.UpcomingBookingFragment

class BookingsPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UpcomingBookingFragment()
            1 -> PastBookingFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}