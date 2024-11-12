package com.atvantiq.parqngomall.ui.features.dashboard.fragments

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.ui.features.notifications.NotificationActivity
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseFragment
import com.atvantiq.parqngomall.databinding.FragmentMoreBinding
import com.atvantiq.parqngomall.ui.adapters.MoreOptionsAdapter
import com.atvantiq.parqngomall.ui.features.aboutUs.AboutUsActivity
import com.atvantiq.parqngomall.ui.features.feedback.FeedbackActivity
import com.atvantiq.parqngomall.ui.features.login.LoginActivity
import com.atvantiq.parqngomall.ui.features.parkingBookings.ParkingBookingActivity
import com.atvantiq.parqngomall.ui.features.payment.PaymentHistoryActivity
import com.atvantiq.parqngomall.ui.features.privacy.PrivacyActivity
import com.atvantiq.parqngomall.ui.features.profile.ProfileActivity
import com.atvantiq.parqngomall.ui.features.support.SupportHelpActivity
import com.atvantiq.parqngomall.ui.viewmodels.dashboard.DashboardVM
import com.atvantiq.parqngomall.utils.Utils
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants


class MoreFragment : BaseFragment<FragmentMoreBinding,DashboardVM>() {

    private lateinit var moreOptionsAdapter: MoreOptionsAdapter

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.fragment_more,DashboardVM::class.java)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setMoreOptions()
    }

    private fun setMoreOptions(){
        moreOptionsAdapter = MoreOptionsAdapter(MoreOptionConstants.items) { it
            when(it){
                MoreOptionConstants.logout ->{
                    logoutFromApp()
                }
                MoreOptionConstants.addFeedback ->{
                    Utils.jumpActivity(requireContext(), FeedbackActivity::class.java)
                }
                MoreOptionConstants.rateTheApp ->{
                    Utils.rateUsDialog(requireContext())
                }
                MoreOptionConstants.helpSupport ->{
                    Utils.jumpActivity(requireContext(), SupportHelpActivity::class.java)
                }
                MoreOptionConstants.privacyPolicy ->{
                    Utils.jumpActivity(requireContext(), PrivacyActivity::class.java)
                }
                MoreOptionConstants.aboutUs ->{
                    Utils.jumpActivity(requireContext(),AboutUsActivity::class.java)
                }
                MoreOptionConstants.myDetails ->{
                    Utils.jumpActivity(requireContext(),ProfileActivity::class.java)
                }
                MoreOptionConstants.paymentHistory ->{
                    Utils.jumpActivity(requireContext(),PaymentHistoryActivity::class.java)
                }
                MoreOptionConstants.notifications ->{
                    Utils.jumpActivity(requireContext(), NotificationActivity::class.java)
                }
                MoreOptionConstants.parkingBooking ->{
                    Utils.jumpActivity(requireContext(), ParkingBookingActivity::class.java)
                }
            }
        }
        binding.recyclerViewSettings.hasFixedSize()
        binding.recyclerViewSettings.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewSettings.adapter = moreOptionsAdapter
    }

    private fun logoutFromApp(){
        alertDialogShow(requireContext(),"Alert","Are you sure want to logout?","Logout",
            okLister = { dialog, which ->
                Utils.jumpActivity(requireContext(),LoginActivity::class.java)
                requireActivity().finish()
                dialog.dismiss()
            }, canelLister = {
                    dialog, which ->
                dialog.dismiss()
            })
    }

    override fun subscribeToEvents(vm: DashboardVM) {

    }

}