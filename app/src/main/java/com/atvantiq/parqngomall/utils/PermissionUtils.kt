package com.atvantiq.parqngomall.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.util.*

object PermissionUtils {

	var LOCATION_PERMISSIONS =arrayOf(
		Manifest.permission.ACCESS_FINE_LOCATION,
		Manifest.permission.ACCESS_COARSE_LOCATION
	)

	private fun checkPermissionGranted(context: Context, permissions: Array<String>): Boolean {
		val deniedPermissions = ArrayList<String>()
		for (permission in permissions) {
			if (ActivityCompat.checkSelfPermission(
					context,
					permission
				) == PackageManager.PERMISSION_DENIED
			) {
				deniedPermissions.add(permission)
			}
		}
		return deniedPermissions.isEmpty()
	}

	fun hasLocationPermissions(context: Context): Boolean {
		return checkPermissionGranted(
			context,
			arrayOf(
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.ACCESS_COARSE_LOCATION
			)
		)
	}

}

