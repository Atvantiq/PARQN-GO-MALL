package com.atvantiq.parqngomall.ui.features.login

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.lifecycle.Observer
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityLoginBinding
import com.atvantiq.parqngomall.network.Status
import com.atvantiq.parqngomall.ui.viewmodels.login.LoginClickEvents
import com.atvantiq.parqngomall.ui.viewmodels.login.LoginErrorHandler
import com.atvantiq.parqngomall.ui.viewmodels.login.LoginVM
import com.atvantiq.parqngomall.utils.PhoneNumberUtils
import com.atvantiq.parqngomall.utils.Utils
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginVM>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_login, LoginVM::class.java)


    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
    }

    private fun setListeners(){
        binding.phoneEmailInput.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if(viewModel.phoneNumber.get()?.isEmpty()==true){
                    PhoneNumberUtils.requestPhoneNumberHint(this,hintResult)
                    binding.phoneEmailInput.requestFocus()
                    binding.phoneEmailInput.error  = null
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        binding.phoneEmailInput.doAfterTextChanged {
            binding.phoneEmailInput.setSelection(binding.phoneEmailInput.length())

        }
    }

    override fun subscribeToEvents(vm: LoginVM) {
        binding.vm = vm

        vm.phoneNumber.addOnPropertyChangedCallback(object :OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
             if(vm.phoneNumber.get()?.isEmpty()==true){
                 binding.isContinueEnable = false
             } else{
                 binding.isContinueEnable = true
             }
            }
        })

        vm.clickEvents.observe(this, Observer {
            when(it){
                LoginClickEvents.ON_PASSWORD_TOGGLE -> {
                }
                LoginClickEvents.ON_LOGIN_CLICK -> {
                    Utils.jumpActivity(this, OTPVerifyActivity::class.java)
                }
                LoginClickEvents.ON_TAP_PHONE_NUMBER ->{
                    PhoneNumberUtils.requestPhoneNumberHint(this,hintResult)
                }
            }
        })

        vm.errorHandler.observe(this, Observer {
            when(it){
                LoginErrorHandler.EMPTY_PHONE_NUMBER -> {
                    binding?.phoneEmailInput?.error = getString(R.string.enterMobileNumber)
                    binding.phoneEmailInput?.requestFocus()
                    shakeEditText(this,binding.phoneEmailInput)


                }

                LoginErrorHandler.EMPTY_PASSWORD -> {

                }
            }
        })

        vm.postResponse.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    dismissCircularProgress()
                    it.response?.forEachIndexed{ index,it ->

                    }
                }

                Status.LOADING -> {
                    showCircularProgress()
                }

                Status.ERROR -> {
                    dismissCircularProgress()

                }
            }
        })
    }



    private var hintResult = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result: ActivityResult? ->
            if (result != null) {
                try {
                    val phoneNum = Identity.getSignInClient(this).getPhoneNumberFromIntent(result.data)
                    // Do your work with phone number here
                    var phoneTriple = PhoneNumberUtils.separatePhoneNumber(phoneNum)
                    Log.e("jaspal","Selected Phone: ${phoneTriple?.first}")
                    Log.e("jaspal","Selected Country Code: ${phoneTriple?.second}")
                    Log.e("jaspal","Selected PhoneWithCountryCode: ${phoneTriple?.third}")
                    viewModel.phoneNumber.set(phoneTriple?.first)
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }

}