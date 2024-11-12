package com.atvantiq.parqngomall.ui.features.profile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityProfileCompleteBinding
import com.atvantiq.parqngomall.ui.viewmodels.profile.ProfileClickEvents
import com.atvantiq.parqngomall.ui.viewmodels.profile.ProfileErrorHandler
import com.atvantiq.parqngomall.ui.viewmodels.profile.ProfileVM

class ProfileCompleteActivity : BaseActivity<ActivityProfileCompleteBinding,ProfileVM>() {


    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_profile_complete,ProfileVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupToolbar()
    }

    private fun setupToolbar(){
        binding.completeProfileToolbar.toolbarTitle.text = "Complete Profile"
        binding.completeProfileToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun subscribeToEvents(vm: ProfileVM) {
        binding.vm = vm

        vm.errorHandler.observe(this, Observer {
            when(it){
                ProfileErrorHandler.EMPTY_FIRST_NAME -> {
                    binding?.firstNameEt?.error = getString(R.string.enter_first_name)
                    binding?.firstNameEt?.requestFocus()
                }
                ProfileErrorHandler.EMPTY_LAST_NAME -> {
                    binding?.lastNameEt?.error = getString(R.string.enter_last_name)
                    binding?.lastNameEt?.requestFocus()
                }
                ProfileErrorHandler.EMPTY_EMAIL -> {
                    binding?.emailEt?.error = getString(R.string.enter_email)
                    binding?.emailEt?.requestFocus()
                }
                ProfileErrorHandler.INVALID_EMAIL ->{
                    binding?.emailEt?.error = getString(R.string.enter_valid_email)
                    binding?.emailEt?.requestFocus()
                }
            }
        })

        vm.clickEvents.observe(this, Observer {
            when(it){
                ProfileClickEvents.ON_SUBMIT_DETAILS -> {
                    finish()
                }
            }
        })
    }

}