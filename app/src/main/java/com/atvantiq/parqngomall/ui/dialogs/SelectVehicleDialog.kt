package com.atvantiq.parqngomall.ui.dialogs

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingBottomSheetFragment
import com.atvantiq.parqngomall.data.model.VehicleDetailItem
import com.atvantiq.parqngomall.databinding.DialogSelectVehicleBinding
import com.atvantiq.parqngomall.ui.adapters.VehicleDetailAdapter
import com.atvantiq.parqngomall.ui.adapters.VehicleListAdapter
import com.atvantiq.parqngomall.utils.DividerItemDecoration
import com.atvantiq.parqngomall.utils.constants.MoreOptionConstants


class SelectVehicleDialog(var onVehicleSelected:(type: VehicleDetailItem)->Unit) : BaseBindingBottomSheetFragment<DialogSelectVehicleBinding>() {

    private lateinit var vehicleDetailAdapter: VehicleListAdapter

    override val fragmentBinding: FragmentBinding
        get() = FragmentBinding(R.layout.dialog_select_vehicle)

    override fun onCreateViewFragment(savedInstanceState: Bundle?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        vehicleDetailAdapter = VehicleListAdapter(MoreOptionConstants.getVehicleList()){
            onVehicleSelected.invoke(it)
            dismiss()
        }
        binding.vehicleList.hasFixedSize()
        binding.vehicleList.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.custom_divider))
        binding.vehicleList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.vehicleList.adapter = vehicleDetailAdapter
    }
}