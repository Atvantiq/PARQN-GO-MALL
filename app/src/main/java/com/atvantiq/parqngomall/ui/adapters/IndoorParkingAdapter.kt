package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.IndoorParkingItem
import com.atvantiq.parqngomall.databinding.ItemIndoorParkingBinding


class IndoorParkingAdapter(private val indoorParkingList: List<IndoorParkingItem>,var onTapParking:(item:IndoorParkingItem)->Unit) : RecyclerView.Adapter<IndoorParkingAdapter.IndoorParkingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndoorParkingViewHolder {
        var binding = DataBindingUtil.inflate<ItemIndoorParkingBinding>(LayoutInflater.from(parent.context),
            R.layout.item_indoor_parking,parent,false)
        return IndoorParkingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndoorParkingViewHolder, position: Int) {
        val parking = indoorParkingList[position]
        if (parking.occupied.toDouble() / parking.total >= 0.8) {
            holder.binding.isAllMostFull =true
        }else{
            holder.binding.isAllMostFull =false
        }
        holder.binding.item = parking
        holder.binding.root.setOnClickListener {
            onTapParking.invoke(parking)
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = indoorParkingList.size

    inner class IndoorParkingViewHolder(var binding:ItemIndoorParkingBinding) : RecyclerView.ViewHolder(binding.root)
}