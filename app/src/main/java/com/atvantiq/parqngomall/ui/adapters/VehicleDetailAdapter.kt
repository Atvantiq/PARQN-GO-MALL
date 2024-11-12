package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.VehicleDetailItem
import com.atvantiq.parqngomall.databinding.ItemVehileDetailBinding


class VehicleDetailAdapter(private val vehicleDetailList: List<VehicleDetailItem>) : RecyclerView.Adapter<VehicleDetailAdapter.ParkingViewHolder>() {

    class ParkingViewHolder(var binding: ItemVehileDetailBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        var infalter = LayoutInflater.from(parent.context)
        var binding =  DataBindingUtil.inflate<ItemVehileDetailBinding>(infalter,
            R.layout.item_vehile_detail,parent,false)
        return ParkingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vehicleDetailList.size
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        holder.binding.item = vehicleDetailList[position]
        holder.binding.executePendingBindings()
    }
}