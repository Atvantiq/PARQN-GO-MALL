package com.atvantiq.parqngomall.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity

object PhoneNumberUtils {

    fun requestPhoneNumberHint(context: Context,hintResult: ActivityResultLauncher<IntentSenderRequest>){
        val request: GetPhoneNumberHintIntentRequest = GetPhoneNumberHintIntentRequest.builder().build()
        Identity.getSignInClient(context)
            .getPhoneNumberHintIntent(request)
            .addOnSuccessListener {
                hintResult.launch(IntentSenderRequest.Builder(it.intentSender).build())
            }
            .addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }
    }

    fun separatePhoneNumber(phoneNumber: String): Triple<String, String,String>? {
        if(phoneNumber.isEmpty()){
            return null
        }
        var trimmedPhoneNumber = phoneNumber.trim()

        if (trimmedPhoneNumber.startsWith("+")) {
            trimmedPhoneNumber = trimmedPhoneNumber.substring(1)
        }
        var phone = trimmedPhoneNumber.substring(trimmedPhoneNumber.length - 10)
        var countryCode = trimmedPhoneNumber.substring(0,trimmedPhoneNumber.length-10)
        return Triple(phone,countryCode,phoneNumber)  // Return null if the country code is not found
    }

}