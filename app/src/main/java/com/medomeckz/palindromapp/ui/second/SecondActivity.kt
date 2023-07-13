package com.medomeckz.palindromapp.ui.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.medomeckz.palindromapp.databinding.ActivitySecondBinding
import com.medomeckz.palindromapp.ui.first.FirstActivity
import com.medomeckz.palindromapp.ui.third.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Second Screen"

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setUp()
        btnNext()
        btnBack()
    }

    private fun btnBack() {
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@SecondActivity, FirstActivity::class.java))
            finish()
        }
    }

    private fun btnNext() {
        binding.btnSelect.setOnClickListener {
            startActivity(Intent(this@SecondActivity, ThirdActivity::class.java))
            finish()
        }
    }

    private fun setUp() {
        val name = intent.getStringExtra(NAME)
        binding.tvName.text = name
    }

    companion object {
        const val NAME = "tagName"
    }
}