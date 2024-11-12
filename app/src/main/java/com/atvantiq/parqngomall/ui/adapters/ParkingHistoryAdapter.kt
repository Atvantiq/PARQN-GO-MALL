package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.ParkingHistoryItem
import com.atvantiq.parqngomall.databinding.ItemParkingHistoryBinding

class ParkingHistoryAdapter(private val parkingHistoryList: List<ParkingHistoryItem>) : RecyclerView.Adapter<ParkingHistoryAdapter.ParkingViewHolder>() {

    class ParkingViewHolder(var binding:ItemParkingHistoryBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        var infalter = LayoutInflater.from(parent.context)
        var binding =  DataBindingUtil.inflate<ItemParkingHistoryBinding>(infalter,
            R.layout.item_parking_history,parent,false)
        return ParkingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parkingHistoryList.size
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        holder.binding.item = parkingHistoryList[position]
        holder.binding.executePendingBindings()
    }
}