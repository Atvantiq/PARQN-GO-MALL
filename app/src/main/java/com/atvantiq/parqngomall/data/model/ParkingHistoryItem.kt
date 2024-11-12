package com.atvantiq.parqngomall.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParkingHistoryItem(
    val id: Int,
    val vehicleNumber: String,
    val parkingType: String,
    val entryTime: String,
    val exitTime: String?,
    val amountPaid: Double
):Parcelable