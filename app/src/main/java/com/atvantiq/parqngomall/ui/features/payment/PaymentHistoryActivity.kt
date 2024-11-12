package com.atvantiq.parqngomall.ui.features.payment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityPaymentHistoryBinding
import com.atvantiq.parqngomall.ui.adapters.PaymentHistoryAdapter
import com.atvantiq.parqngomall.ui.viewmodels.paymentHistory.PaymentHistoryVM
import com.atvantiq.parqngomall.utils.DividerItemDecoration
import com.atvantiq.parqngomall.utils.Utils

class PaymentHistoryActivity : BaseActivity<ActivityPaymentHistoryBinding,PaymentHistoryVM>() {
    private lateinit var paymentHistoryAdapter: PaymentHistoryAdapter

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_payment_history,PaymentHistoryVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupToolbar()
        initPaymentHistoryList()
    }

    private fun setupToolbar(){
        binding.paymentHistoryToolbar.toolbarTitle.text = "Payment History"
        binding.paymentHistoryToolbar.toolbarBackBt.setOnClickListener {
            finish()
        }
    }

    private fun initPaymentHistoryList(){
        paymentHistoryAdapter = PaymentHistoryAdapter{
            Utils.jumpActivity(this,PaymentDetailsActivity::class.java)
        }
        binding.paymentHistoryList.adapter = paymentHistoryAdapter
    }

    override fun subscribeToEvents(vm: PaymentHistoryVM) {

    }

}