package com.atvantiq.parqngomall.ui.features.notifications

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atvantiq.parqngo.ui.viewmodels.notification.NotificationVM
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityNotificationBinding
import com.atvantiq.parqngomall.ui.adapters.SearchNotificationAdapter
import com.atvantiq.parqngomall.ui.features.extendTime.ExtendTimeActivity
import com.atvantiq.parqngomall.utils.Utils


class NotificationActivity : BaseActivity<ActivityNotificationBinding, NotificationVM>() {

    private var pageNumber = 0
    private var limit = 10
    private var loading = true
    private var isScrolling = false
    private var isFirstLoading = true
    private lateinit var notificationAdapter: SearchNotificationAdapter

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_notification,NotificationVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initToolbar()
        initNotificationList()
    }

    private fun initToolbar(){
        binding.notificationToolbar.toolbarTitle.text = "Notifications"
        binding.notificationToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initNotificationList() {
        var layoutManager = LinearLayoutManager(this)
        binding.searhNotificationList.layoutManager = layoutManager
        notificationAdapter = SearchNotificationAdapter{
            Utils.jumpActivity(this,ExtendTimeActivity::class.java)
        }
        binding.searhNotificationList.adapter = notificationAdapter
        binding.searhNotificationList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (dy > 0) {
                    if (loading) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                            loading = false
                            pageNumber += 1
                            isScrolling = true
                            isFirstLoading = false
                            notificationAdapter?.hideProgressBar(false)
                        }
                    }
                }
            }
        })
    }

    override fun subscribeToEvents(vm: NotificationVM) {

    }
}