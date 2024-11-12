package com.atvantiq.parqngomall.ui.features.parkingBookings

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingActivity
import com.atvantiq.parqngomall.databinding.ActivityParkingBookingBinding
import com.atvantiq.parqngomall.ui.adapters.BookingsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ParkingBookingActivity : BaseBindingActivity<ActivityParkingBookingBinding>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_parking_booking)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupToolbar()
        setTabBarWithViewPager()
    }

    private fun setupToolbar(){
        binding.bookingToolbar.toolbarTitle.text = "Parking Bookings"
        binding.bookingToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setTabBarWithViewPager(){
        binding.viewPager.adapter = BookingsPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Upcoming Bookings"
                1 -> "Past Bookings"
                else -> null
            }
        }.attach()
    }
}