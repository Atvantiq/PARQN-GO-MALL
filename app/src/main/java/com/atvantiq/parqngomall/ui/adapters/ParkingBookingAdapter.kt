package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.ParkingHistoryItem
import com.atvantiq.parqngomall.databinding.ItemBookingBinding
import com.atvantiq.parqngomall.databinding.ItemParkingHistoryBinding

class ParkingBookingAdapter(var isPastBooking:Boolean) : RecyclerView.Adapter<ParkingBookingAdapter.ParkingViewHolder>() {

    class ParkingViewHolder(var binding:ItemBookingBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        var infalter = LayoutInflater.from(parent.context)
        var binding =  DataBindingUtil.inflate<ItemBookingBinding>(infalter,
            R.layout.item_booking,parent,false)
        return ParkingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        holder.binding.isPastBookings = isPastBooking
        holder.binding.executePendingBindings()
    }
}