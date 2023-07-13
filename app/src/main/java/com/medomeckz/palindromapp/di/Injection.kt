package com.medomeckz.palindromapp.di

import android.content.Context
import com.medomeckz.palindromapp.data.remote.ApiClient
import com.medomeckz.palindromapp.repo.PalindromeRepository

object Injection {
    fun provideRepository(context: Context): PalindromeRepository {
        val apiService = ApiClient.appService
        return PalindromeRepository(apiService)
    }
}