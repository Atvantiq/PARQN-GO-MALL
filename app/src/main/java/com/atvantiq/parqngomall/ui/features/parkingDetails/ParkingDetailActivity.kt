package com.atvantiq.parqngomall.ui.features.parkingDetails

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atvantiq.parqngomall.BuildConfig
import com.atvantiq.parqngomall.R
import com.atvantiq.parqngomall.base.BaseActivity
import com.atvantiq.parqngomall.databinding.ActivityParkingDetailBinding
import com.atvantiq.parqngomall.ui.adapters.ImageSliderAdapter
import com.atvantiq.parqngomall.ui.dialogs.BookDateTimeDialog
import com.atvantiq.parqngomall.ui.viewmodels.parking.ParkingVM
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class ParkingDetailActivity : BaseActivity<ActivityParkingDetailBinding,ParkingVM>() ,
    PaymentResultWithDataListener,
    ExternalWalletListener {

    private val imageList = listOf(R.drawable.parking, R.drawable.parking2, R.drawable.parking3)
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    override val bindingActivity: ActivityBinding
        get() = ActivityBinding(R.layout.activity_parking_detail,ParkingVM::class.java)

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Checkout.preload(this)
        initSlider()
        autoSlideImages()
        initListeners()
    }

    private fun initListeners(){
        binding.backPressBt.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.bookButton.setOnClickListener {
            BookDateTimeDialog{ selectedDate, selectedTime ->
                makePayment()
                finish()
            }.show(supportFragmentManager,"bookDateTimeDialog")
        }
    }

    private fun initSlider(){
        binding.parkingImageSlider.adapter = ImageSliderAdapter(imageList)
    }

    private fun autoSlideImages() {
        val runnable = object : Runnable {
            override fun run() {
                if (currentPage == imageList.size) {
                    currentPage = 0
                }
                binding.parkingImageSlider.currentItem = currentPage++
                handler.postDelayed(this, 3000) // Slide interval (3 seconds)
            }
        }
        handler.post(runnable)
    }

    override fun subscribeToEvents(vm: ParkingVM) {

    }
    private fun makePayment(){
        var checkout = Checkout()
        checkout.setKeyID(BuildConfig.PAYMENT_KEY)
        try {
            val options = JSONObject()
            options.put("name", "Atvantiq")
            options.put("description", "Testing Parking Payments")
            options.put("theme.color", "#FF4800")
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency", "INR")
            options.put("amount", "1")
            //options.put("send_sms_hash", true)
            //options.put("remember_customer", true)

            val preFill = JSONObject()
            preFill.put("email", "test@atvantiq.com")
            preFill.put("contact", "9590603080")

            options.put("prefill", preFill)
            checkout.open(this,options)

        }catch (e: Exception){
            Toast.makeText(this,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }


    override fun onPaymentSuccess(razorpayPaymentID: String?, paymentData: PaymentData?) {
        Toast.makeText(this, "Payment is successful : $razorpayPaymentID", Toast.LENGTH_SHORT).show()

    }

    override fun onPaymentError(errorCode: Int, response: String?, paymentData: PaymentData?) {
        Toast.makeText(this, "Payment Failed due to error : $response", Toast.LENGTH_SHORT).show()
        when(errorCode){
            Checkout.NETWORK_ERROR ->{
                // for network error / loss of the internet
            }
            Checkout.INVALID_OPTIONS ->{
                // error due to options passed to checkout
            }
            Checkout.PAYMENT_CANCELED ->{
                // user cancel the payment
            }
            Checkout.TLS_ERROR ->{
                // device is not supporting
            }
        }
    }

    override fun onExternalWalletSelected(p0: String?, data: PaymentData?) {

    }

}