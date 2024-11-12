package com.atvantiq.parqngomall.ui.viewmodels.login

import ApiState
import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atvantiq.parqngomall.data.model.Posts
import com.atvantiq.parqngomall.data.repository.auth.IAuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginVM @Inject constructor(
    application: Application,
    private val authRep: IAuthRepo
) : AndroidViewModel(application) {

    /*
    * Variables Declarations
    * */

    var isPasswordVisible = true

    var phoneNumber = ObservableField<String>().apply {
        set("")
    }
    var password = ObservableField<String>().apply {
        set("")
    }

    var clickEvents = MutableLiveData<LoginClickEvents>()
    var errorHandler = MutableLiveData<LoginErrorHandler>()

    /*
    * Methods
    * */
    fun onForgetPasswordClick() {
       // clickEvents.value = LoginClickEvents.ON_FORGET_PASSWORD_CLICK
    }

    fun onSubmitLoginClick() {
        if (isValidLoginDetails()) {
            clickEvents.value = LoginClickEvents.ON_LOGIN_CLICK
        }
    }

    fun onNewUserClick() {
       // clickEvents.value = LoginClickEvents.ON_NEW_USER_CLICK
    }

    fun onPasswordToggleClick() {
        clickEvents.value = LoginClickEvents.ON_PASSWORD_TOGGLE
    }

    fun onTapPhoneNumber(){
        clickEvents.value = LoginClickEvents.ON_TAP_PHONE_NUMBER
    }


    /*
    * Validate Methods
    * */
    private fun isValidLoginDetails(): Boolean {
        return when {
            phoneNumber.get().toString().trim().isEmpty() -> {
                errorHandler.value = LoginErrorHandler.EMPTY_PHONE_NUMBER
                false
            }
            else -> {
                true
            }
        }
    }


    /*API Methods*/

    var postResponse = MutableLiveData<ApiState<Posts>>()

    fun loadPostData() {
        /*viewModelScope.launch {
            postResponse.postValue(ApiState.loading())
            authRep.loginRequest()
                .catch { e ->
                    postResponse.postValue(ApiState.error(e))
                }
                .collect { result ->
                    postResponse.postValue(ApiState.success(response))
                }
        }*/
        viewModelScope.launch {
            postResponse.postValue(ApiState.loading())
            try {
               var  response = authRep.loginRequest()
               postResponse.postValue(ApiState.success(response))
            }catch (e:Exception){
                postResponse.postValue(ApiState.error(e))
            }
        }

    }


}