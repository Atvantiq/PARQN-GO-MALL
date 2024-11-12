package com.atvantiq.parqngomall.utils.bindingAdapter

import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.databinding.BindingAdapter
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.IndoorParkingItem

object BindingUtils {

    @JvmStatic
    @BindingAdapter(value = ["firstString", "secondString"])
    fun concatText(textView: TextView, firstString: String?, secondString: String?) {
        var firstStr = ""
        var secondStr = ""

        if (firstString != null) {
            firstStr = firstString.toString()
        }
        if (secondString != null) {
            secondStr = secondString
        }
        textView.text =
            String.format(textView.context.getString(R.string.concatText), firstStr, secondStr)
    }

    @JvmStatic
    @BindingAdapter(value = ["checkParkingStatus"])
    fun checkParkingStatus(textView: TextView, parking: IndoorParkingItem) {
        var parkingStatus = parking.occupied.toDouble() / parking.total
        if(parkingStatus >= 0.8){
            textView.setTextColor(ActivityCompat.getColor(textView.context, R.color.darkRed))
        }else if(parkingStatus >= 0.7 && parkingStatus < 0.8){
            textView.setTextColor(ActivityCompat.getColor(textView.context, R.color.orange))
        }else{
            textView.setTextColor(ActivityCompat.getColor(textView.context, R.color.darkGray))
        }
    }

}