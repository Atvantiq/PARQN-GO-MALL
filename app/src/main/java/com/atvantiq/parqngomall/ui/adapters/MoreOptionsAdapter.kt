package com.atvantiq.parqngomall.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.SettingItem
import com.atvantiq.parqngomall.databinding.ItemMoreHeadingBinding
import com.atvantiq.parqngomall.databinding.ItemMoreOptionsBinding


class MoreOptionsAdapter(private val items: List<SettingItem>, private val onOptionClick: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val TYPE_HEADING = 0
        const val TYPE_OPTION = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SettingItem.Heading -> TYPE_HEADING
            is SettingItem.Option -> TYPE_OPTION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADING -> {
                val inflater  = LayoutInflater.from(parent.context)
                val binding: ItemMoreHeadingBinding = DataBindingUtil.inflate(inflater,R.layout.item_more_heading,parent,false)
                return HeadingViewHolder(binding)
            }
            TYPE_OPTION -> {
                val inflater  = LayoutInflater.from(parent.context)
                val binding: ItemMoreOptionsBinding = DataBindingUtil.inflate(inflater, R.layout.item_more_options,parent,false)
                return OptionViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is SettingItem.Heading -> (holder as HeadingViewHolder).binding.item = item
            is SettingItem.Option -> {
                (holder as OptionViewHolder).binding.item = item
                (holder).binding.root.setOnClickListener { onOptionClick(item.title) }
            }
        }
    }

    override fun getItemCount() = items.size

    inner class HeadingViewHolder(var binding:ItemMoreHeadingBinding) : RecyclerView.ViewHolder(binding.root)

    inner class OptionViewHolder(var binding:ItemMoreOptionsBinding) : RecyclerView.ViewHolder(binding.root)
}
