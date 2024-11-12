package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.VehicleType
import com.atvantiq.parqngomall.databinding.ItemVehicleTypeBinding


class VehicleTypeAdapter(
    private val vehicleTypes: List<VehicleType>,
    private val onItemClick: (VehicleType) -> Unit
) : RecyclerView.Adapter<VehicleTypeAdapter.VehicleTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleTypeViewHolder {
        var binding = DataBindingUtil.inflate<ItemVehicleTypeBinding>(LayoutInflater.from(parent.context), R.layout.item_vehicle_type,parent,false)
        return VehicleTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleTypeViewHolder, position: Int) {
        val vehicleType = vehicleTypes[position]
        holder.binding.textViewVehicleType.text = vehicleType.name
        holder.binding.root.setOnClickListener {
            onItemClick.invoke(vehicleType)
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = vehicleTypes.size

    inner class VehicleTypeViewHolder(var binding:ItemVehicleTypeBinding) : RecyclerView.ViewHolder(binding.root)
}