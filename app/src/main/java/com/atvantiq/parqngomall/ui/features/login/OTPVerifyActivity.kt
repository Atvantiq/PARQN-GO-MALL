package com.atvantiq.parqngomall.ui.features.login

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingActivity
import com.atvantiq.parqngomall.databinding.ActivityOtpverifyBinding
import com.atvantiq.parqngomall.ui.features.dashboard.DashboardActivity
import com.atvantiq.parqngomall.utils.Utils

class OTPVerifyActivity : BaseBindingActivity<ActivityOtpverifyBinding>() {

    private var otpCode: String = ""

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_otpverify)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initToolbar()
        setupOtpInputs()
        setListeners()
        startTimer()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.otpToolbar.customToolbarWhite)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Disable default title
        binding.otpToolbar.toolbarTitle.text = "OTP Verification"
        binding.otpToolbar.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupOtpInputs() {
        binding.otpBox1.requestFocus()

        binding.otpBox1.doOnTextChanged { text, _, _, _ ->
            if (text?.length == 1) binding.otpBox2.requestFocus()
        }

        binding.otpBox2.doOnTextChanged { text, _, _, _ ->
            if (text?.length == 1) binding.otpBox3.requestFocus()
            else if (text?.isEmpty() == true) binding.otpBox1.requestFocus()
        }

        binding.otpBox3.doOnTextChanged { text, _, _, _ ->
            if (text?.length == 1) binding.otpBox4.requestFocus()
            else if (text?.isEmpty() == true) binding.otpBox2.requestFocus()
        }

        binding.otpBox4.doOnTextChanged { text, _, _, _ ->
            if (text?.isNotEmpty() == true) {
                binding.btnSubmit.isEnabled = true
            } else if (text?.isEmpty() == true) {
                binding.otpBox3.requestFocus()
                binding.btnSubmit.isEnabled = false
            }
        }

        binding.otpBox1.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_DEL && binding.otpBox1.text.isEmpty()) {
            }
            false
        }

        binding.otpBox2.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_DEL && binding.otpBox2.text.isEmpty()) {
                binding.otpBox1.text.clear()
                binding.otpBox1.requestFocus()
            }
            false
        }

        binding.otpBox3.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_DEL && binding.otpBox3.text.isEmpty()) {
                binding.otpBox2.text.clear()
                binding.otpBox2.requestFocus()
            }
            false
        }

        binding.otpBox4.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_DEL && binding.otpBox4.text.isEmpty()) {
                binding.otpBox3.text.clear()
                binding.otpBox3.requestFocus()
            }
            false
        }
    }

    private fun setListeners() {
        binding.btnSubmit.setOnClickListener {
            otpCode = binding.otpBox1.text.toString() + binding.otpBox2.text.toString() +
                    binding.otpBox3.text.toString() + binding.otpBox4.text.toString()
            Toast.makeText(this, otpCode, Toast.LENGTH_SHORT).show()
            Utils.jumpActivity(this,DashboardActivity::class.java)
            finish()
        }
    }

    private fun startTimer() {
        var timer = object : CountDownTimer(29000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.remainingSeconds = (millisUntilFinished / 1000).toString()
            }


            override fun onFinish() {
                binding.isTimerFinished = true
            }
        }
        timer.start()
    }

}
