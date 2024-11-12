package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.databinding.ItemImageSliderBinding

class ImageSliderAdapter(private val images: List<Int>) : RecyclerView.Adapter<ImageSliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        var binding = DataBindingUtil.inflate<ItemImageSliderBinding>(LayoutInflater.from(parent.context),
            R.layout.item_image_slider, parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.binding.imageRes = images[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = images.size

    inner class SliderViewHolder(var binding:ItemImageSliderBinding) : RecyclerView.ViewHolder(binding.root)
}
