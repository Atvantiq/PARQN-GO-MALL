package com.atvantiq.parqngomall.ui.features.parkingHistory

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingActivity
import com.atvantiq.parqngomall.databinding.ActivityParkingHistoryBinding
import com.atvantiq.parqngomall.ui.adapters.ParkingHistoryAdapter
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants

class ParkingHistoryActivity : BaseBindingActivity<ActivityParkingHistoryBinding>() {

    private lateinit var parkingHistoryAdapter: ParkingHistoryAdapter

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_parking_history)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        parkingHistory()
    }

    private fun parkingHistory() {
        parkingHistoryAdapter = ParkingHistoryAdapter(MoreOptionConstants.getParkingHistory())
        binding.vehicleHistoryList.hasFixedSize()
        binding.vehicleHistoryList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        binding.vehicleHistoryList.adapter = parkingHistoryAdapter
    }
}