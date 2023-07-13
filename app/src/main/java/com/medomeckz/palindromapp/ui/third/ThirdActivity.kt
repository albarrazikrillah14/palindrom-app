package com.medomeckz.palindromapp.ui.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.medomeckz.palindromapp.ViewModelFactory
import com.medomeckz.palindromapp.adapter.AppAdapter
import com.medomeckz.palindromapp.adapter.LoadingAdapter
import com.medomeckz.palindromapp.databinding.ActivityThirdBinding
import com.medomeckz.palindromapp.ui.first.FirstActivity
import com.medomeckz.palindromapp.ui.second.SecondActivity

class ThirdActivity : AppCompatActivity() {
    private lateinit var thirdViewModel: ThirdViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
        btnBack()
    }

    private fun btnBack() {
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@ThirdActivity, FirstActivity::class.java))
            finish()
        }
    }

    private fun setUp() {
        factory = ViewModelFactory.getInstance(this)
        thirdViewModel = ViewModelProvider(this, factory)[ThirdViewModel::class.java]
        binding.rvAccount.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData() {
        val adapter = AppAdapter()
        binding.rvAccount.adapter = adapter.withLoadStateFooter(
            footer =  LoadingAdapter{
                adapter.retry()
            }
        )
        thirdViewModel.getUser.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}