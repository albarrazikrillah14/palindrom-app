package com.medomeckz.palindromapp.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.medomeckz.palindromapp.adapter.AppPagingSource
import com.medomeckz.palindromapp.data.model.DataItem
import com.medomeckz.palindromapp.data.remote.AppService

class PalindromeRepository(private val apiService: AppService) {
    fun checkPalindrome(text: String): Boolean {
        val first = text.lowercase().replace("\\s+".toRegex(), "")
        val second = first.reversed()

        return first == second
    }

    fun getUser(): LiveData<PagingData<DataItem>> {
        return  Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                AppPagingSource(apiService)
            }
        ).liveData
    }

}