package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.databinding.ItemBrandBinding


class ViewBrandAdapter(var logoImages: List<Int>) : RecyclerView.Adapter<ViewBrandAdapter.Holder>() {

    class Holder(var binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var inflater = LayoutInflater.from(parent.context)
        var binding: ItemBrandBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_brand, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.imagePath = logoImages[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return 10
    }
}