package com.atvantiq.parqngomall.ui.features.support

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.ui.viewmodels.support.SupportHelpVM
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivitySupportHelpBinding


class SupportHelpActivity : BaseActivity<ActivitySupportHelpBinding, SupportHelpVM>() {


    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_support_help, SupportHelpVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initToolbar()
    }

    private fun initToolbar(){
        binding.supportToolbar.toolbarTitle.text = "Customer Support"
        binding.supportToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun subscribeToEvents(vm: SupportHelpVM) {

    }

}