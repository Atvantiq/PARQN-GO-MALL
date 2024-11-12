package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.ParkingHistoryItem
import com.atvantiq.parqngomall.databinding.ItemParkingHistoryBinding
import com.atvantiq.parqngomall.databinding.ItemPaymentHistoryBinding

class PaymentHistoryAdapter(var onTapPaymentHistory:()->Unit) : RecyclerView.Adapter<PaymentHistoryAdapter.ParkingViewHolder>() {

    class ParkingViewHolder(var binding:ItemPaymentHistoryBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        var infalter = LayoutInflater.from(parent.context)
        var binding =  DataBindingUtil.inflate<ItemPaymentHistoryBinding>(infalter,
            R.layout.item_payment_history,parent,false)
        return ParkingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            onTapPaymentHistory.invoke()
        }
        holder.binding.executePendingBindings()
    }
}