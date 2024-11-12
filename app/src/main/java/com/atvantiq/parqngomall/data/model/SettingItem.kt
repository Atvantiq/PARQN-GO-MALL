package com.atvantiq.parqngomall.data.model

sealed class SettingItem {
    data class Heading(val title: String) : SettingItem()
    data class Option(val iconResId: Int, val title: String) : SettingItem()
}