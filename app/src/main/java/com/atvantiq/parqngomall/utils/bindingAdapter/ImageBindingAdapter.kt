package com.atvantiq.parqngomall.utils.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView,resourceId:Int){
        imageView.setImageResource(resourceId)
    }
}