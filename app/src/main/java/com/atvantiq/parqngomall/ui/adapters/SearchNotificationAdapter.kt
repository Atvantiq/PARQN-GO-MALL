package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.databinding.ItemSearchNotificationBinding
import com.atvantiq.parqngomall.widgets.FooterRecyclerView


class SearchNotificationAdapter(var onTapExtendTime:()->Unit) : FooterRecyclerView() {
	
	val ITEM_VIEW = 1
	
	override fun count(): Int {
		return 4
	}
	
	override fun viewType(): Int {
		return ITEM_VIEW
	}
	
	override fun onCreateHolderMethod(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		var binding = DataBindingUtil.inflate<ItemSearchNotificationBinding>(
			LayoutInflater.from(parent.context), R.layout.item_search_notification,
			parent,
			false
		)
		return Holder(binding)
	}
	
	override fun onBindViewHolderMethod(holder: RecyclerView.ViewHolder, position: Int) {
		if(holder is Holder){
			holder.binding.extendTimeBt.setOnClickListener {
				onTapExtendTime.invoke()
			}
		}
	}

	class Holder(var binding: ItemSearchNotificationBinding) : RecyclerView.ViewHolder(binding.root)
}