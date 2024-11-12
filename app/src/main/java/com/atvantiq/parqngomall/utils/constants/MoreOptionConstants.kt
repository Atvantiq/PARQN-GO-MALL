package com.atvantiq.parqngomall.utils.constants

import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.data.model.IndoorParkingItem
import com.atvantiq.parqngomall.data.model.ParkingHistoryItem
import com.atvantiq.parqngomall.data.model.SearchParkingItem
import com.atvantiq.parqngomall.data.model.SettingItem
import com.atvantiq.parqngomall.data.model.VehicleDetailItem

object MoreOptionConstants {

    const val myDetails: String = "My Details"
    const val myPaymentMethod: String = "My Payment methods"
    const val myNotifications: String = "My notifications"
    const val helpSupport: String = "Help and support"
    const val addFeedback: String = "Add feedback"
    const val privacyPolicy: String = "Privacy Policy"
    const val shareParqn: String = "Share PARQN-GO"
    const val rateTheApp: String = "Rate the App"
    const val aboutUs: String = "About us"
    const val logout:String = "Logout App"
    const val paymentHistory = "Payment History"
    const val notifications = "Notification"
    const val parkingBooking = "Parking Bookings"

    val items = listOf(
        SettingItem.Heading("Details"),
        SettingItem.Option(R.drawable.ic_help_outline_24, myDetails),
        SettingItem.Option(R.drawable.ic_help_outline_24, parkingBooking),
        SettingItem.Heading("Payments"),
        SettingItem.Option(R.drawable.ic_help_outline_24, paymentHistory),
        SettingItem.Heading("Notifications"),
        SettingItem.Option(R.drawable.ic_help_outline_24, notifications),
        SettingItem.Heading("Help"),
        SettingItem.Option(R.drawable.ic_help_outline_24, helpSupport),
        SettingItem.Option(R.drawable.ic_help_outline_24, addFeedback),
        SettingItem.Heading("About"),
        SettingItem.Option(R.drawable.ic_help_outline_24, privacyPolicy),
        SettingItem.Option(R.drawable.ic_help_outline_24, rateTheApp),
        SettingItem.Option(R.drawable.ic_help_outline_24, aboutUs),
        SettingItem.Heading("Logout"),
        SettingItem.Option(R.drawable.ic_help_outline_24, logout),
        )

    fun getParkingHistory(): List<ParkingHistoryItem> {
        // Replace with actual data retrieval logic
        return listOf(
            ParkingHistoryItem(1, "AB123CD", "Outdoor Parking", "10:00 AM", "12:00 PM", 10.0),
            ParkingHistoryItem(2, "EF456GH", "Basement 1", "11:00 AM", null, 0.0)
        )
    }

    fun getParkingList(): List<IndoorParkingItem>{
        return  listOf(
            IndoorParkingItem("Basement 1", total = 100, occupied = 80),
            IndoorParkingItem("Basement 2", total = 150, occupied = 80),
            IndoorParkingItem("Basement 3", total = 200, occupied = 120)
        )
    }

    fun getLogoImages() : List<Int>{
        return  listOf(
            R.drawable.b1,
            R.drawable.b2,
            R.drawable.b3,
            R.drawable.b4,
            R.drawable.b5,
            R.drawable.b6,
            R.drawable.b7,
            R.drawable.b8,
            R.drawable.b9,
            R.drawable.b10,
        )
    }

    fun getVehicleList(): List<VehicleDetailItem> {
        // Replace with actual data retrieval logic
        return listOf(
            VehicleDetailItem("Tiago", "CH01CB9798", "2020"),
            VehicleDetailItem("MG Hector", "PB46CB9798", "2014"),
            VehicleDetailItem("SUV 700", "HR90CB9798", "2016"),
            VehicleDetailItem("ScorpioN", "HP34CB9798", "2022")
        )
    }
}