package com.atvantiq.parqngomall.ui.features.dashboard.brands

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityViewAllBrandsBinding
import com.atvantiq.parqngomall.ui.adapters.ViewBrandAdapter
import com.atvantiq.parqngomall.ui.viewmodels.viewBrands.ViewBrandsVM
import com.atvantiq.parqngomall.utils.Utils
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants
import com.atvantiq.parqngomall.widgets.GridSpacingItemDecoration

class ViewAllBrandsActivity : BaseActivity<ActivityViewAllBrandsBinding,ViewBrandsVM>() {

    private lateinit var brandAdapter: ViewBrandAdapter

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_view_all_brands,ViewBrandsVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initToolbar()
        setupSearchBar()
        viewAllBrandGridLayout()
    }

    private fun initToolbar(){
        binding.brandsToolbar.toolbarTitle.text = "Available Brands"
        binding.brandsToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupSearchBar(){
        binding.searchBrands.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    //adapter.filter(it)
                    Log.e("jaspal", "Search -2-: $it")
                }
                return false
            }
        })
    }

    private fun viewAllBrandGridLayout() {
        var layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        binding.brandList.layoutManager = layoutManager
        binding.brandList.addItemDecoration(GridSpacingItemDecoration(
                3, Utils.dpToPx(resources.getDimension(R.dimen.margin_small_extra), this), false))
        brandAdapter = ViewBrandAdapter(MoreOptionConstants.getLogoImages())
        binding.brandList.adapter = brandAdapter
    }


    override fun subscribeToEvents(vm: ViewBrandsVM) {

    }

}