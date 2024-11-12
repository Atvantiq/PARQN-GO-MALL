package com.atvantiq.parqngomall.ui.features.privacy

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseBindingActivity
import com.atvantiq.parqngomall.databinding.ActivityPrivacyBinding

class PrivacyActivity : BaseBindingActivity<ActivityPrivacyBinding>() {

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_privacy)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupToolbar()
        loadTermsURL("https://atvantiq.com/policies.php")
    }

    private fun setupToolbar(){
        binding.privacyToolbar.toolbarTitle.text = "Privacy Policy"
        binding.privacyToolbar.toolbarBackBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadTermsURL(url: String) {
        binding?.termsUrlWebView?.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?) {
                Toast.makeText(this@PrivacyActivity, description, Toast.LENGTH_SHORT).show()
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.hideProgressBar = false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.hideProgressBar = true
            }
        }
        binding?.termsUrlWebView?.settings?.javaScriptEnabled = true
        binding?.termsUrlWebView?.loadUrl(url)
    }

}