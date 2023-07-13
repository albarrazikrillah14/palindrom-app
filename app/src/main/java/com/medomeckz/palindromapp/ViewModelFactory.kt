package com.medomeckz.palindromapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medomeckz.palindromapp.di.Injection
import com.medomeckz.palindromapp.repo.PalindromeRepository
import com.medomeckz.palindromapp.ui.first.FirstViewModel
import com.medomeckz.palindromapp.ui.third.ThirdViewModel

class ViewModelFactory(private val repo: PalindromeRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FirstViewModel::class.java) -> {
                FirstViewModel(repo) as T
            }
            modelClass.isAssignableFrom(ThirdViewModel::class.java) -> {
                ThirdViewModel(repo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}