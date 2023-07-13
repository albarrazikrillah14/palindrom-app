package com.medomeckz.palindromapp.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.medomeckz.palindromapp.data.model.DataItem
import com.medomeckz.palindromapp.repo.PalindromeRepository

class ThirdViewModel(private val repo: PalindromeRepository): ViewModel() {
    val getUser: LiveData<PagingData<DataItem>> = repo.getUser().cachedIn(viewModelScope)
}