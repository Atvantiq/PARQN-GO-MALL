package com.atvantiq.parqngomall.ui.features.feedback

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityFeedbackBinding
import com.atvantiq.parqngomall.ui.viewmodels.feedback.FeedbackVM


class FeedbackActivity : BaseActivity<ActivityFeedbackBinding, FeedbackVM>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_feedback,FeedbackVM::class.java)

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
        binding.feedbackToolbar.toolbarTitle.text = "Feedback"
        binding.feedbackToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun subscribeToEvents(vm: FeedbackVM) {

    }

}