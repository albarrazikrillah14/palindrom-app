package com.medomeckz.palindromapp.ui.first

import AnswerFragment
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.medomeckz.palindromapp.R
import com.medomeckz.palindromapp.ViewModelFactory
import com.medomeckz.palindromapp.databinding.ActivityFirstBinding
import com.medomeckz.palindromapp.ui.second.SecondActivity
import com.medomeckz.palindromapp.utils.Constant

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    private lateinit var firstViewModel: FirstViewModel
    private lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setUp()
        toSecondActivity()
        btnCheck()
        playAnimation()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivSample, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val name = ObjectAnimator.ofFloat(binding.tiName, View.ALPHA, 1f).setDuration(500)
        val palindrome = ObjectAnimator.ofFloat(binding.tiPalindrome, View.ALPHA, 1f).setDuration(500)
        val check = ObjectAnimator.ofFloat(binding.btnCheck, View.ALPHA, 1f).setDuration(500)
        val next = ObjectAnimator.ofFloat(binding.btnNext, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(name, palindrome, check, next)
        }.start()
    }

    private fun btnCheck() {
        binding.btnCheck.setOnClickListener {
            if(checkInput()) {
                val text = binding.edtPalindrome.text
                val result = firstViewModel.checkPalindrome(text.toString())

                val answerFragment = AnswerFragment()
                val bundle = Bundle()
                bundle.putString(AnswerFragment.TEXT, text.toString())
                bundle.putBoolean(AnswerFragment.RESULT, result)
                answerFragment.arguments = bundle

                answerFragment.show(supportFragmentManager, AnswerFragment::class.java.simpleName)
            }
        }
    }

    private fun setUp() {
        Glide.with(this)
            .load(Constant.imgUrlPalindrome)
            .into(binding.ivSample)

        factory = ViewModelFactory.getInstance(this)
        firstViewModel = ViewModelProvider(this, factory)[FirstViewModel::class.java]
    }

    private fun toSecondActivity() {
        binding.btnNext.setOnClickListener {
            if(checkInput()) {
                val intent = Intent(this@FirstActivity, SecondActivity::class.java)
                intent.putExtra(SecondActivity.NAME, binding.edtName.text.toString())
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, getString(R.string.check_input), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkInput(): Boolean {
        var isEmpty = false

        binding.apply {
            if(edtName.text?.isEmpty()!!) {
                isEmpty = true
                edtName.error = getString(R.string.check_input)
            }
            if(edtPalindrome.text?.isEmpty()!!) {
                isEmpty = true
                edtPalindrome.error = getString(R.string.check_input)
            }
        }

        return !isEmpty
    }

}