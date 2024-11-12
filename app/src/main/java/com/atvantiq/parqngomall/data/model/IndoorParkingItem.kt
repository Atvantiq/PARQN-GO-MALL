package com.atvantiq.parqngomall.data.model

data class IndoorParkingItem(
    val name: String,
    val total: Int,
    val occupied: Int
) {
    val free: Int
        get() = total - occupied
}