package com.atvantiq.parqngomall.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.databinding.DialogProfileCompleteBinding
import com.atvantiq.parqngomall.ui.features.profile.ProfileCompleteActivity
import com.atvantiq.parqngomall.utils.Utils

class ProfileCompletionDialog() : DialogFragment() {
	lateinit var binding:DialogProfileCompleteBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_ParqnGo)
	}
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = DataBindingUtil.inflate(inflater,R.layout.dialog_profile_complete, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
		setListeners()
	}

	private fun setListeners(){
		binding.continueBt.setOnClickListener {
			dismiss()
			Utils.jumpActivity(requireContext(),ProfileCompleteActivity::class.java)
		}
		binding.closeBt.setOnClickListener {
			dismiss()
		}
	}
	
	override fun onStart() {
		super.onStart()
		val dialog = dialog
		if (dialog != null) {
			val width = ViewGroup.LayoutParams.MATCH_PARENT
			val height = ViewGroup.LayoutParams.MATCH_PARENT
			dialog.window?.setLayout(width, height)
			isCancelable = false
		}
	}
}