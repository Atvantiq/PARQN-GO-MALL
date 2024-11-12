package com.atvantiq.parqngomall.ui.features.dashboard.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseFragment
import com.atvantiq.parqngomall.databinding.FragmentParkingBinding
import com.atvantiq.parqngomall.ui.adapters.BrandsHorizontalAdapter
import com.atvantiq.parqngomall.ui.adapters.ImageSliderAdapter
import com.atvantiq.parqngomall.ui.adapters.IndoorParkingAdapter
import com.atvantiq.parqngomall.ui.features.dashboard.brands.ViewAllBrandsActivity
import com.atvantiq.parqngomall.ui.features.parkingDetails.ParkingDetailActivity
import com.atvantiq.parqngomall.ui.viewmodels.parking.ParkingVM
import com.atvantiq.parqngomall.utils.Utils
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import java.util.ArrayList
import java.util.Timer


class ParkingFragment : BaseFragment<FragmentParkingBinding,ParkingVM>() {

    private lateinit var indoorParkingAdapter:IndoorParkingAdapter
    private lateinit var brandsAdapter:BrandsHorizontalAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var timer: Timer = Timer()
    private var currentPage = 0

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.fragment_parking,ParkingVM::class.java)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun subscribeToEvents(vm: ParkingVM) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setListeners()
        setRadiusBannerImage()
        setupIndoorParkingListing()
        setupBrandsHorizontalList()
        createImageSlider()
    }

    private fun setListeners(){
        binding.cardOutDoorParking.setOnClickListener {
            Utils.jumpActivity(requireContext(),ParkingDetailActivity::class.java)
        }

        binding.brandViewAllBt.setOnClickListener {
            Utils.jumpActivity(requireContext(),ViewAllBrandsActivity::class.java)
        }
    }

    private fun setRadiusBannerImage(){
        binding.shapeImageView.setShapeAppearanceModel(binding.shapeImageView.getShapeAppearanceModel()
            .toBuilder()
            .setBottomRightCorner(CornerFamily.ROUNDED,50f)
            .setBottomLeftCorner(CornerFamily.ROUNDED,50f)
            .build());
    }

    private fun setupBrandsHorizontalList() {
        var layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoriesList.layoutManager = layoutManager
        brandsAdapter = BrandsHorizontalAdapter(MoreOptionConstants.getLogoImages())
        binding.categoriesList.adapter = brandsAdapter
    }


    private fun setupIndoorParkingListing(){
        indoorParkingAdapter = IndoorParkingAdapter(MoreOptionConstants.getParkingList()){
            Utils.jumpActivity(requireContext(),ParkingDetailActivity::class.java)
        }
        binding.indoorParkingList.hasFixedSize()
        binding.indoorParkingList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.indoorParkingList.adapter = indoorParkingAdapter
    }

    private fun createImageSlider() {
        binding.productImagePager.offscreenPageLimit = 1

        var imageArrayList: ArrayList<Int> = ArrayList()
        imageArrayList.add(R.drawable.d1)
        imageArrayList.add(R.drawable.d2)
        imageArrayList.add(R.drawable.d3)

        var pagerAdapter = ImageSliderAdapter(imageArrayList)
        binding.productImagePager.adapter = pagerAdapter
        autoSlideImages(imageArrayList)
    }

    private fun autoSlideImages(imageArrayList: ArrayList<Int>) {
        val runnable = object : Runnable {
            override fun run() {
                if (currentPage == imageArrayList.size) {
                    currentPage = 0
                }
                binding.productImagePager.currentItem = currentPage++
                handler.postDelayed(this, 3000) // Slide interval (3 seconds)
            }
        }
        handler.post(runnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(timer!=null){
            timer.cancel()
        }
    }
}