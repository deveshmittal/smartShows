package com.devesh.smartshows.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devesh.smartshows.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}