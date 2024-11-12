package com.atvantiq.parqngomall.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchParkingItem(var parkingName:String, var hours:String, var distance:String,var address:String,val lat: Double,var lang: Double):Parcelable
