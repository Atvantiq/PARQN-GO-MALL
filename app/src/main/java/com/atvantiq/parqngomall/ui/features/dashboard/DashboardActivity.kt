package com.atvantiq.parqngomall.ui.features.dashboard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.CornerRadius
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityDashboardBinding
import com.atvantiq.parqngomall.ui.dialogs.ProfileCompletionDialog
import com.atvantiq.parqngomall.ui.viewmodels.dashboard.DashboardVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class DashboardActivity : BaseActivity<ActivityDashboardBinding,DashboardVM>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_dashboard,DashboardVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initNavigationController()
        showProfileCompleteDialog()
    }

    private fun initNavigationController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.dashboard_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation

        // Set up the BottomNavigationView with NavController
        bottomNavigationView.setupWithNavController(navController)

        val shapeDrawable : MaterialShapeDrawable = binding.bottomNavigation.background as MaterialShapeDrawable
        shapeDrawable.setShapeAppearanceModel(shapeDrawable.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED,50f)
            .setTopLeftCorner(CornerFamily.ROUNDED,50f)
            .build());
    }

    override fun subscribeToEvents(vm: DashboardVM) {

    }

    private fun showProfileCompleteDialog(){
        ProfileCompletionDialog().show(supportFragmentManager,"profileCompleteDialog")
    }

}