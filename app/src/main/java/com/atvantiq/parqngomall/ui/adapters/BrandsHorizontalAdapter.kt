package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.databinding.ItemHorizontalBrandsBinding

class BrandsHorizontalAdapter(var logoImages: List<Int>) : RecyclerView.Adapter<BrandsHorizontalAdapter.Holder>() {
	
	class Holder(var binding:ItemHorizontalBrandsBinding) : RecyclerView.ViewHolder(binding.root)
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		var inflater = LayoutInflater.from(parent.context)
		var binding: ItemHorizontalBrandsBinding =
			DataBindingUtil.inflate(inflater, R.layout.item_horizontal_brands, parent, false)
		return Holder(binding)
	}
	
	override fun onBindViewHolder(holder: Holder, position: Int) {
		holder.binding.imagePath = logoImages[position]
		holder.binding.executePendingBindings()
	}
	
	override fun getItemCount(): Int {
		return logoImages.size
	}
}