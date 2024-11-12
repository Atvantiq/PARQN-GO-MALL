package com.atvantiq.parqngomall.ui.features.payment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityPaymentDetailsBinding
import com.atvantiq.parqngomall.ui.viewmodels.paymentHistory.PaymentHistoryVM

class PaymentDetailsActivity : BaseActivity<ActivityPaymentDetailsBinding,PaymentHistoryVM>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_payment_details,PaymentHistoryVM::class.java)

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
        binding.paymentHistoryToolbar.toolbarTitle.text = "Payment Details"
        binding.paymentHistoryToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun subscribeToEvents(vm: PaymentHistoryVM) {

    }
}