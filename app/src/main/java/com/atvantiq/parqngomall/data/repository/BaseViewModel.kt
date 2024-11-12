package com.atvantiq.parqngomall.data.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
	
	var networkError = MutableLiveData<Boolean>()
	
}