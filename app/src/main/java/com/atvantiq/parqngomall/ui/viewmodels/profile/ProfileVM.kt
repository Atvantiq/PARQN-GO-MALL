package com.atvantiq.parqngomall.ui.viewmodels.profile

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.atvantiq.parqngomall.data.repository.BaseViewModel
import com.atvantiq.parqngomall.utils.ValidationUtils

class ProfileVM(application: Application) : BaseViewModel(application) {

    /*
    * Variables Declarations
    * */
    var clickEvents = MutableLiveData<ProfileClickEvents>()
    var errorHandler = MutableLiveData<ProfileErrorHandler>()

    var firstName = ObservableField<String>().apply {
        set("")
    }

    var lastName = ObservableField<String>().apply {
        set("")
    }

    var phone = ObservableField<String>().apply {
        set("")
    }

    var email = ObservableField<String>().apply {
        set("")
    }

    /*
    * Methods
    * */
    fun onSubmitDetails() {
        if (isValidSubmitDetails()) {
            clickEvents.value = ProfileClickEvents.ON_SUBMIT_DETAILS
        }
    }

    /*
    * Validation Methods
    * */
    private fun isValidSubmitDetails(): Boolean {
        return when {
            firstName.get().toString().trim().isNullOrEmpty() -> {
                errorHandler.value = ProfileErrorHandler.EMPTY_FIRST_NAME
                false
            }

            lastName.get().toString().trim().isNullOrEmpty() -> {
                errorHandler.value = ProfileErrorHandler.EMPTY_LAST_NAME
                false
            }

            email.get().toString().trim().isNullOrEmpty() -> {
                errorHandler.value = ProfileErrorHandler.EMPTY_EMAIL
                false
            }

            !ValidationUtils.isValidEmail(email.get().toString().trim()) -> {
                errorHandler.value = ProfileErrorHandler.INVALID_EMAIL
                false
            }

            else -> {
                true
            }
        }
    }

}