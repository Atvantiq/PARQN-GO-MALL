package com.atvantiq.parqngomall.ui.features.extendTime

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingActivity
import com.atvantiq.parqngomall.databinding.ActivityExtendTimeBinding

class ExtendTimeActivity : BaseBindingActivity<ActivityExtendTimeBinding>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_extend_time)

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
        binding.extendTimeToobar.toolbarTitle.text = "Extend Time"
        binding.extendTimeToobar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}