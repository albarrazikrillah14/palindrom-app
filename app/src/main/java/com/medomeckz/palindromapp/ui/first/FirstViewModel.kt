package com.medomeckz.palindromapp.ui.first

import androidx.lifecycle.ViewModel
import com.medomeckz.palindromapp.repo.PalindromeRepository

class FirstViewModel(private val repo: PalindromeRepository): ViewModel() {
    fun checkPalindrome(text: String): Boolean = repo.checkPalindrome(text)
}