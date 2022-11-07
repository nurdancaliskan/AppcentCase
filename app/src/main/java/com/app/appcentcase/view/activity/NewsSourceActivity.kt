package com.app.appcentcase.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.appcentcase.databinding.ActivityNewsSourceBinding

class NewsSourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsSourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        val source = intent.getStringExtra("source")
        source?.let { binding.webView.loadUrl(it) }
    }

    private fun initListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}